package com.app.springboot.testing.controller;

import com.app.springboot.testing.domain.models.Account;
import com.app.springboot.testing.domain.models.dto.TransferDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.MethodOrderer.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.*;

@Tag("integracion_restttemplate")
@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
class AccountControllerResttemplateTest {

    @Autowired
    private TestRestTemplate client;

    private ObjectMapper mapper;

    // Automáticamente nos importa el puerto
    @LocalServerPort
    private int port;

    @BeforeEach
    void setup() {
        mapper = new ObjectMapper();
    }

    /*
        Es importante agregar que, si en la ruta del consumo de los endpoints
        agregamos http://localhost:8080/api/account.....
        Esto nos obliga a levantar el proyecto
     */

    @Test
    @Order(1)
    void transferTest() throws JsonProcessingException {
        // Given
        TransferDto dto = new TransferDto();
        dto.setAmount(new BigDecimal("100"));
        dto.setOriginAccountId(1L);
        dto.setDestinationAccountId(2L);
        dto.setBankId(1L);

        ResponseEntity<String> response = client.postForEntity(createUri("/api/accounts/transfer"), dto, String.class);

        String json = response.getBody();
        System.out.println(port);
        assertNotNull(json);
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
        assertTrue(json.contains("transfer successful"));
        assertEquals(HttpStatus.OK, response.getStatusCode());

        JsonNode jsonNode = mapper.readTree(json);
        assertEquals("transfer successful", jsonNode.path("message").asText());
        assertEquals(LocalDate.now().toString(), jsonNode.path("date").asText());
        assertEquals("100", jsonNode.path("transaction").path("amount").asText());
        assertEquals(1, jsonNode.path("transaction").path("originAccountId").asLong());
    }

    @Test
    @Order(2)
    void accountDetailTest() {
        ResponseEntity<Account> response = client.getForEntity(createUri("/api/accounts/1"), Account.class);
        Account account = response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());

        assertNotNull(account);
        assertEquals(1L, account.getId());
        assertEquals("Cristian Real", account.getPerson());
        assertEquals("900.00", account.getBalance().toPlainString());
        assertEquals(new Account(1L, "Cristian Real", new BigDecimal("900.00")), account);
    }

    @Test
    @Order(3)
    void listTest() throws JsonProcessingException {
        ResponseEntity<Account[]> response = client.getForEntity(createUri("/api/accounts/"), Account[].class);
        List<Account> listAccounts = Arrays.asList(response.getBody());

        assertNotNull(response);
        assertTrue(listAccounts.size() > 1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());

        assertEquals(1L, listAccounts.get(0).getId());
        assertEquals("Cristian Real", listAccounts.get(0).getPerson());
        assertEquals("900.00", listAccounts.get(0).getBalance().toPlainString());

        assertEquals(2L, listAccounts.get(1).getId());
        assertEquals("Maria Hazelt", listAccounts.get(1).getPerson());
        assertEquals("3100.00", listAccounts.get(1).getBalance().toPlainString());

        JsonNode json = mapper.readTree(mapper.writeValueAsString(listAccounts));
        assertEquals(1L, json.get(0).path("id").asLong());
        assertEquals("Cristian Real", json.get(0).path("person").asText());
        assertEquals("900.0", json.get(0).path("balance").asText());

        assertEquals(2L, json.get(1).path("id").asLong());
        assertEquals("Maria Hazelt", json.get(1).path("person").asText());
        assertEquals("3100.0", json.get(1).path("balance").asText());
    }

    @Test
    @Order(4)
    void saveTest() {
        Account account = new Account(null, "Pepe", new BigDecimal("1000"));
        ResponseEntity<Account> response = client.postForEntity(createUri("/api/accounts/"), account, Account.class);
        Account ac = response.getBody();

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());

        assertNotNull(ac);
        assertEquals(4L, ac.getId());
        assertEquals("Pepe", ac.getPerson());
        assertEquals("1000", ac.getBalance().toPlainString());
    }

    @Test
    @Order(5)
    void deleteTest() {
        client.delete(createUri("/api/accounts/4"));

        ResponseEntity<Account[]> response = client.getForEntity(createUri("/api/accounts/"), Account[].class);
        List<Account> listAccounts = Arrays.asList(response.getBody());

        assertNotNull(response);
        assertTrue(listAccounts.size() > 1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
    }

    @Test
    @Order(6)
    void deleteOtherTest() {
        //client.delete(createUri("/api/accounts/2"));
        // En exchange los parámetros de la ruta se pasan como path variable
        // client.exchange(createUri("/api/accounts/{id}"), HttpMethod.DELETE, null, Void.class, XX); // Se pueden pasar los parámetros en un mapa
        //ResponseEntity<Void> responseExchange = client.exchange(createUri("/api/accounts/2"), HttpMethod.DELETE, null, Void.class);

        Map<String, Object> pathVariables = new HashMap<>();
        pathVariables.put("id", 3);
        ResponseEntity<Void> responseExchange =
                client.exchange(createUri("/api/accounts/{id}"), HttpMethod.DELETE, null, Void.class, pathVariables);

        assertEquals(HttpStatus.NO_CONTENT, responseExchange.getStatusCode());
        assertFalse(responseExchange.hasBody());

        ResponseEntity<Account[]> response = client.getForEntity(createUri("/api/accounts/"), Account[].class);
        List<Account> listAccounts = Arrays.asList(response.getBody());

        assertNotNull(response);
        assertTrue(listAccounts.size() > 1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
    }

    private String createUri(String uri) {
        return "http://localhost:"+ port + uri;
    }
}