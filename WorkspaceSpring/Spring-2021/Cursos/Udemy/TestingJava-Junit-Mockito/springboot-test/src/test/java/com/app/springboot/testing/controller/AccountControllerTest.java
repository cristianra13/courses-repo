package com.app.springboot.testing.controller;

import com.app.springboot.testing.domain.models.Account;
import com.app.springboot.testing.domain.models.dto.TransferDto;
import com.app.springboot.testing.domain.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static com.app.springboot.testing.data.Data.*;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AccountController.class)
class AccountControllerTest {

    private final String path = "/api/accounts/";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    private ObjectMapper mapper;

    @BeforeEach
    public void setup() {
        mapper = new ObjectMapper();
    }

    @Test
    public void detailAccountTest() throws Exception {
        // Given - contexto
        when(accountService.findById(anyLong())).thenReturn(createAccount001().orElseThrow());

        // When
        mockMvc.perform(get("/api/accounts/1").contentType(MediaType.APPLICATION_JSON))
                // then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.person").value("Cristian Real"))
                .andExpect(jsonPath("$.balance").value("1000"));

        verify(accountService).findById(anyLong());
    }

    @Test
    public void transferTest() throws Exception {
        // Given
        TransferDto dto = new TransferDto();
        dto.setOriginAccountId(1L);
        dto.setDestinationAccountId(2L);
        dto.setAmount(new BigDecimal("100"));
        dto.setBankId(1L);

        String json = mapper.writeValueAsString(dto);

        // When
        mockMvc.perform(post("/api/accounts/transfer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                // Then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.date").value(LocalDate.now().toString()))
                .andExpect(jsonPath("$.message").value("transfer successful"))
                .andExpect(jsonPath("$.transaction.originAccountId").value(1L));
    }

    @Test
    void findAllTest() throws Exception {
        // Given
        List<Account> accounts = getAccounts();
        when(accountService.findAll()).thenReturn(accounts);

        // When
        mockMvc.perform(get("/api/accounts/").contentType(MediaType.APPLICATION_JSON))
                //then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Como se retorna un array o una lista en el json, con $[0].person tomamos cada valor de cada posición
                .andExpect(jsonPath("$[0].person").value("Cristian Real"))
                .andExpect(jsonPath("$[0].balance").value("1000"))
                .andExpect(jsonPath("$[1].person").value("John Doe"))
                .andExpect(jsonPath("$[1].balance").value("3000"))
                .andExpect(jsonPath("$", hasSize(2))) // validamos la cantidad de elementos
                .andExpect(content().json(mapper.writeValueAsString(accountService.findAll())));

        verify(accountService, times(2)).findAll();
    }

    @Test
    void saveTest() throws Exception {
        //Given
        Account account = new Account(null, "Cristian", new BigDecimal("25000"));
        when(accountService.save(any())).then((invocation) -> {
            Account  ac = invocation.getArgument(0);
            ac.setId(3L);
            return ac;
        });

        // When
        mockMvc.perform(post(path).contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(account)))
                //Then
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // is() es igual a un equals o a un .value()
                .andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.person", is("Cristian")))
                .andExpect(jsonPath("$.balance", is(25000)));

        verify(accountService).save(any());
    }

    private List<Account> getAccounts() {
        return Arrays.asList(createAccount001().orElseThrow(), createAccount002().orElseThrow());
    }
}