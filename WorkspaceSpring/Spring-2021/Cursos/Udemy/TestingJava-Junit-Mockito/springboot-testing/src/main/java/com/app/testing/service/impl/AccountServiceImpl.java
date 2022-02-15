package com.app.testing.service.impl;

import com.app.testing.models.Account;
import com.app.testing.models.Bank;
import com.app.testing.repository.AccountRepository;
import com.app.testing.repository.BankRepository;
import com.app.testing.service.AccountService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;
    private BankRepository bankRepository;

    public AccountServiceImpl(AccountRepository accountRepository, BankRepository bankRepository) {
        this.accountRepository = accountRepository;
        this.bankRepository = bankRepository;
    }

    @Override
    public Account findById(Long id) {
        return accountRepository.findById(id).orElseThrow();
    }

    @Override
    public int reviewTotalTranfers(Long bankId) {
        Bank bank = bankRepository.findById(bankId).orElseThrow();
        return bank.getTotalTransfers();
    }

    @Override
    public BigDecimal reviewBalance(Long accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow();

        return account.getBalance();
    }

    @Override
    public void tranfer(Long accountOrigin, Long accountDest, BigDecimal amount, Long bankId) {
        // Debit do origin
        Account origin = accountRepository.findById(accountOrigin).orElseThrow();
        origin.debit(amount);
        accountRepository.save(origin);

        // Credit to the destination
        Account dest = accountRepository.findById(accountDest).orElseThrow();
        dest.credit(amount);
        accountRepository.save(dest);

        Bank bank = bankRepository.findById(bankId).orElseThrow();
        int totalTranfers = bank.getTotalTransfers();
        bank.setTotalTransfers(++totalTranfers);
        bankRepository.save(bank);
    }
}
