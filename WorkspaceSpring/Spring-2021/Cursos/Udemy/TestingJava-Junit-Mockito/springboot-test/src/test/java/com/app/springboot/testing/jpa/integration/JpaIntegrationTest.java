package com.app.springboot.testing.jpa.integration;

import com.app.springboot.testing.domain.models.Account;
import com.app.springboot.testing.repository.AccountRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Tag("integracion_jpa")
@DataJpaTest
@ActiveProfiles("test")
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
        assertEquals(3, accounts.size());
    }

    @Test
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

    @Test
    public void updateTest() {
        // given
        Account account = new Account(null, "Cristian Real", new BigDecimal("1000"));

        // when
        Account savedAccount = accountRepository.save(account);
        // Account accountPerson = accountRepository.findByPerson("Cristian Real").orElseThrow();

        //then
        assertEquals("Cristian Real", savedAccount.getPerson());
        assertEquals("1000", savedAccount.getBalance().toPlainString());

        // When
        account.setBalance(new BigDecimal("3800"));
        Account accountSave = accountRepository.save(account);

        assertEquals("Cristian Real", accountSave.getPerson());
        assertEquals("3800", accountSave.getBalance().toPlainString());
    }


    @Test
    void deleteTest() {
        Account account = accountRepository.findById(2L).orElseThrow();
        assertEquals("Maria Hazelt", account.getPerson());

        accountRepository.delete(account);

        assertThrows(NoSuchElementException.class, () -> {
            accountRepository.findByPerson("Maria Hazelt").orElseThrow();
        });
    }
}
