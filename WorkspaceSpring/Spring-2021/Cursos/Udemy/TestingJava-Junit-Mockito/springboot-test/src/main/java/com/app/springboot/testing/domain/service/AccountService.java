package com.app.springboot.testing.domain.service;

import com.app.springboot.testing.domain.models.Account;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {

    List<Account> findAll();

    Account findById(Long id);

    Account save(Account account);

    void deleteById(Long id);

    int reviewTotalTranfers(Long bankId);

    BigDecimal reviewBalance(Long accountId);

    void tranfer(Long accountOrigin, Long accountDest, BigDecimal amount, Long bankId);
}
