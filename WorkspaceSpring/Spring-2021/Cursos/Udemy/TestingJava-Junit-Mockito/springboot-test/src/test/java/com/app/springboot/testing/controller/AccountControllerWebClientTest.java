package com.app.springboot.testing.controller;

import com.app.springboot.testing.domain.models.Account;
import com.app.springboot.testing.domain.models.dto.TransferDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.MethodOrderer.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.*;
import static org.springframework.http.MediaType.*;

@Tag("integracion_webclient")
@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class AccountControllerWebClientTest {
    // private final String uri = "http://localhost:8080/api/accounts";

    @Autowired
    private WebTestClient webTestClient;

    @Test
    @Order(1)
    void transferTest() throws JsonProcessingException {
        TransferDto transferDto = new TransferDto();
        transferDto.setOriginAccountId(1L);
        transferDto.setDestinationAccountId(2L);
        transferDto.setBankId(1L);
        transferDto.setAmount(new BigDecimal("100"));

        Map<String, Object> response = new HashMap<>();
        response.put("date", LocalDate.now().toString());
        response.put("status", "OK");
        response.put("message", "transfer successful");
        response.put("transaction", transferDto);

        ObjectMapper mapper = new ObjectMapper();

        // When
        /*
        No es necesario agregar http://loca.... ya que si se tiene el proyecto en emismo server de las pruebas,
        toma esta misma url
         */
        webTestClient.post().uri("/api/accounts/transfer")
                .contentType(APPLICATION_JSON)
                .bodyValue(transferDto)
                .exchange()
                // then
                .expectStatus().isOk()
                .expectHeader().contentType(APPLICATION_JSON)
                /*
                    en .expectBody() también podemos agregar la clase del objeto que queremos
                    esperar También podemos agregar si queremos la respuesta de algún tipo de dato
                 */
                .expectBody()
                .consumeWith(resp -> { // recibimos la respuesta del serve como json
                    // esto es lo mismo que esta fuera de este lambda, pero de otra manera
                    try {
                        JsonNode json = mapper.readTree(resp.getResponseBody());
                        assertEquals("transfer successful", json.path("message").asText());
                        assertEquals(1L, json.path("transaction").path("originAccountId").asLong());
                        assertEquals(2L, json.path("transaction").path("destinationAccountId").asLong());
                        assertEquals(LocalDate.now().toString(), json.path("date").asText());
                        assertEquals("100", json.path("transaction").path("amount").asText());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                })
                .jsonPath("$.message").isNotEmpty()
                .jsonPath("$.message").value(is("transfer successful"))
                .jsonPath("$.message").value(valor -> assertEquals("transfer successful", valor)) // otra forma
                .jsonPath("$.message").isEqualTo("transfer successful") // otra forma
                .jsonPath("$.transaction.originAccountId").isEqualTo(transferDto.getOriginAccountId())
                .jsonPath("$.date").isEqualTo(LocalDate.now().toString())
                .json(mapper.writeValueAsString(response));
    }

    @Test
    @Order(2)
    void detailAccountTest() {
        webTestClient.get().uri("/api/accounts/1")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.person").isEqualTo("Cristian Real")
                .jsonPath("$.balance").isEqualTo("900.0");
    }

    @Test
    @Order(3)
    void detailAccount2Test() {
        webTestClient.get().uri("/api/accounts/2")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(APPLICATION_JSON)
                .expectBody(Account.class)
                .consumeWith(res -> {
                    Account account = res.getResponseBody();
                    assertEquals("Maria Hazelt", account.getPerson());
                    assertEquals("3100.00", account.getBalance().toPlainString());
                });
    }

    @Test
    @Order(4)
    void listTest() {
        webTestClient.get().uri("/api/accounts/")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(APPLICATION_JSON)
                .expectBody()
                .jsonPath("$[0].person").isEqualTo("Cristian Real")
                .jsonPath("$[0].id").isEqualTo(1)
                .jsonPath("$[0].balance").isEqualTo(900.0)
                .jsonPath("$[1].person").isEqualTo("Maria Hazelt")
                .jsonPath("$[1].id").isEqualTo(2)
                .jsonPath("$[1].balance").isEqualTo(3100.0)
                .jsonPath("$").isArray() // validamos si es array
                .jsonPath("$").value(hasSize(3)); // validamos la cantidad de elementos
    }

    @Test
    @Order(5)
    void list2Test() {
        webTestClient.get().uri("/api/accounts/")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(APPLICATION_JSON)
                .expectBodyList(Account.class) // esperamos el body como un listado de Cuentas
                .consumeWith(resp -> {
                    List<Account> accounts = resp.getResponseBody();
                    assertFalse(accounts.isEmpty());

                    assertEquals("Cristian Real", accounts.get(0).getPerson());
                    assertEquals(1, accounts.get(0).getId());
                    assertEquals("900.0", accounts.get(0).getBalance().toPlainString());

                    assertEquals("Maria Hazelt", accounts.get(1).getPerson());
                    assertEquals(2, accounts.get(1).getId());
                    assertEquals("3100.0", accounts.get(1).getBalance().toPlainString());
                })
                .hasSize(3);
    }

    @Test
    @Order(6)
    void saveTest() {
        // Given
        Account account = new Account(null, "Hanna Smith", new BigDecimal("2800"));

        // When
        webTestClient.post().uri("/api/accounts/")
                .contentType(APPLICATION_JSON)
                .bodyValue(account)
                .exchange()
        // Then
                .expectStatus().isCreated()
                .expectHeader().contentType(APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.person").isEqualTo("Hanna Smith")
                .jsonPath("$.id").isEqualTo(4)
                .jsonPath("$.balance").isEqualTo(2800);
    }

    @Test
    @Order(7)
    void save2Test() {
        // Given
        Account account = new Account(null, "Juan", new BigDecimal("1300"));

        // When
        webTestClient.post().uri("/api/accounts/")
                .contentType(APPLICATION_JSON)
                .bodyValue(account)
                .exchange()
                // Then
                .expectStatus().isCreated()
                .expectHeader().contentType(APPLICATION_JSON)
                .expectBody(Account.class)
                .consumeWith(res -> {
                    Account ac = res.getResponseBody();
                    assertNotNull(ac);
                    assertEquals(5, ac.getId());
                    assertEquals("Juan", ac.getPerson());
                    assertEquals(1300, ac.getBalance().intValue());
                });
    }

    @Test
    @Order(8)
    void deleteTest() {
        // validamos la lista inicialmente
        webTestClient.get().uri("/api/accounts/")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Account.class)
                .hasSize(5);

        webTestClient.delete().uri("/api/accounts/5")
                .exchange()
                .expectStatus().isNoContent()
                .expectBody().isEmpty();

        // validamos la lista finalmente
        webTestClient.get().uri("/api/accounts/")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Account.class)
                .hasSize(4);

        // validamos que no exista el id
        webTestClient.get().uri("/api/accounts/5")
                .exchange()
                .expectStatus().isNotFound()
                .expectBody().isEmpty();
    }
}
