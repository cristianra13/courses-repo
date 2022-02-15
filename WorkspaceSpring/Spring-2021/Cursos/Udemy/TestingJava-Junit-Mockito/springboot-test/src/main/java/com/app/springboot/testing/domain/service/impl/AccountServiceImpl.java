package com.app.springboot.testing.domain.service.impl;

import com.app.springboot.testing.domain.models.Account;
import com.app.springboot.testing.domain.models.Bank;
import com.app.springboot.testing.domain.service.AccountService;
import com.app.springboot.testing.repository.AccountRepository;
import com.app.springboot.testing.repository.BankRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;
    private BankRepository bankRepository;

    public AccountServiceImpl(AccountRepository accountRepository, BankRepository bankRepository) {
        this.accountRepository = accountRepository;
        this.bankRepository = bankRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Account findById(Long id) {
        return accountRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        accountRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public int reviewTotalTranfers(Long bankId) {
        Bank bank = bankRepository.findById(bankId).orElseThrow();
        return bank.getTotalTransfers();
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal reviewBalance(Long accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow();

        return account.getBalance();
    }

    @Override
    @Transactional
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
