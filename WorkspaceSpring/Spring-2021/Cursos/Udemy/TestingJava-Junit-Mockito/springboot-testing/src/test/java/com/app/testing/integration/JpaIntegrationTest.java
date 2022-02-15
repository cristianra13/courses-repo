package com.app.testing.integration;

import com.app.testing.models.Account;
import com.app.testing.repository.AccountRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class JpaIntegrationTest {

    @Autowired
    AccountRepository accountRepository;

    @Test
    void findByIdTest() {
        Optional<Account> account = accountRepository.findById(1L);
        System.out.println(accountRepository.findAll());
        assertTrue(account.isPresent());
        assertEquals("Cristian Real", account.orElseThrow().getPerson());
    }

    @Test
    void findByPersonTest() {
        Optional<Account> account = accountRepository.findByPerson("Cristian Real");
        System.out.println(accountRepository.findAll());
        assertTrue(account.isPresent());
        assertEquals("Cristian Real", account.orElseThrow().getPerson());
        assertEquals("1000.00", account.orElseThrow().getBalance().toPlainString());
    }

    @Test
    void findByPersonExceptionTest() {
        Optional<Account> account = accountRepository.findByPerson("Rodel");
        assertThrows(NoSuchElementException.class, account::orElseThrow);
        assertFalse(account.isPresent());
    }

    @Test
    public void findAllTest() {
        List<Account> accounts = accountRepository.findAll();
        assertFalse(accounts.isEmpty());
        assertEquals(2, accounts.size());
    }

    @Test
    @Order(1)
    public void saveTest() {
        // given
        Account account = new Account(null, "Cristian Real", new BigDecimal("1000"));
        Account savedAccount = accountRepository.save(account);

        // when
        // Account accountPerson = accountRepository.findByPerson("Cristian Real").orElseThrow();

        //then
        assertEquals("Cristian Real", savedAccount.getPerson());
        assertEquals("1000", savedAccount.getBalance().toPlainString());
    }

    // TODO: hacer los teste faltantes cuando se revise la inserción de datos

}
