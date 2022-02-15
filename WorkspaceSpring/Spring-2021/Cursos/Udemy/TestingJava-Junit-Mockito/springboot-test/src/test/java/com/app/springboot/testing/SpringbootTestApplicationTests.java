package com.app.springboot.testing;

import com.app.springboot.testing.data.Data;
import com.app.springboot.testing.domain.exception.InsufficientMoneyException;
import com.app.springboot.testing.domain.models.Account;
import com.app.springboot.testing.domain.models.Bank;
import com.app.springboot.testing.domain.service.AccountService;
import com.app.springboot.testing.domain.service.impl.AccountServiceImpl;
import com.app.springboot.testing.repository.AccountRepository;
import com.app.springboot.testing.repository.BankRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static com.app.springboot.testing.data.Data.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class SpringbootTestApplicationTests {

    @MockBean
    private AccountRepository accountRepository;

    @MockBean
    private BankRepository bankRepository;

    @Autowired
    private AccountService accountService;

    @BeforeEach
    void setup() {
        /*accountRepository = mock(AccountRepository.class);
        bankRepository = mock(BankRepository.class);
        accountService = new AccountServiceImpl(accountRepository, bankRepository);*/
    }

    @Test
    void contextLoads() {
        when(accountRepository.findById(1L)).thenReturn(createAccount001());
        when(accountRepository.findById(2L)).thenReturn(createAccount002());
        when(bankRepository.findById(1L)).thenReturn(createBank001());

        // Before to do transfer
        BigDecimal originBalance = accountService.reviewBalance(1L);
        BigDecimal destBalance = accountService.reviewBalance(2L);

        assertEquals("1000", originBalance.toPlainString());
        assertEquals("3000", destBalance.toPlainString());

        accountService.tranfer(1L, 2L, new BigDecimal("100"), 1L);

        // after to do transfer
        originBalance = accountService.reviewBalance(1L);
        destBalance = accountService.reviewBalance(2L);

        assertEquals("900", originBalance.toPlainString());
        assertEquals("3100", destBalance.toPlainString());

        int totalTransfers = accountService.reviewTotalTranfers(1L);
        assertEquals(1, totalTransfers);

        // Number invocations (times by default is 1)
        verify(accountRepository, times(3)).findById(1L);
        verify(accountRepository, times(3)).findById(2L);

        verify(accountRepository, times(2)).save(any(Account.class));

        verify(bankRepository, times(2)).findById(1L);
        verify(bankRepository).save(any(Bank.class));

        verify(accountRepository, times(6)).findById(anyLong());
        verify(accountRepository, never()).findAll();
    }

    @Test
    void tranferWithInsufficientMoneyTest() {
        when(accountRepository.findById(1L)).thenReturn(createAccount001());
        when(accountRepository.findById(2L)).thenReturn(createAccount002());
        when(bankRepository.findById(1L)).thenReturn(createBank001());

        // Before to do transfer
        BigDecimal originBalance = accountService.reviewBalance(1L);
        BigDecimal destBalance = accountService.reviewBalance(2L);

        assertEquals("1000", originBalance.toPlainString());
        assertEquals("3000", destBalance.toPlainString());

        assertThrows(InsufficientMoneyException.class, () -> accountService.tranfer(1L, 2L, new BigDecimal("1100"), 1L));

        // after to do transfer
        originBalance = accountService.reviewBalance(1L);
        destBalance = accountService.reviewBalance(2L);

        assertEquals("1000", originBalance.toPlainString());
        assertEquals("3000", destBalance.toPlainString());

        int totalTransfers = accountService.reviewTotalTranfers(1L);
        assertEquals(0, totalTransfers);

        // Number invocations (times by default is 1)
        verify(accountRepository, times(3)).findById(1L);
        verify(accountRepository, times(2)).findById(2L);
        verify(accountRepository, never()).save(any(Account.class));

        verify(bankRepository, times(1)).findById(1L);
        verify(bankRepository, never()).save(any(Bank.class));

        verify(accountRepository, times(5)).findById(anyLong());
        verify(accountRepository, never()).findAll();
    }

    @Test
    void verifySameObjectTest() {
        when(accountRepository.findById(1L)).thenReturn(Data.createAccount001());

        Account account01 = accountService.findById(1L);
        Account account02 = accountService.findById(1L);

        assertSame(account01, account02);
        assertTrue( account01 == account02);
        verify(accountRepository, times(2)).findById(1L);
    }

    @Test
    void findAllTest() {
        // Given
        when(accountRepository.findAll()).thenReturn(getAccounts());

        // When
        List<Account> accounts = accountService.findAll();

        assertFalse(accounts.isEmpty());
        assertEquals(2, accounts.size());
        assertTrue(accounts.contains(createAccount001().orElseThrow()));

        verify(accountRepository).findAll();
    }

    @Test
    void saveTest() {
        Account account = new Account(null, "Cristian", new BigDecimal("3000"));

        // Cuando se invoque el save, se toma el argumento y agregamos el id con 5
        when(accountRepository.save(any())).then(invocation -> {
            Account ac = invocation.getArgument(0);
            ac.setId(5L);
            return ac;
        });

        // When
        Account saved = accountService.save(account);

        // Then
        assertEquals("Cristian", saved.getPerson());
        assertEquals(5, saved.getId());
        assertEquals("3000", saved.getBalance().toPlainString());

        verify(accountRepository).save(any());
    }

    private List<Account> getAccounts() {
        return Arrays.asList(createAccount001().orElseThrow(), createAccount002().orElseThrow());
    }
}
