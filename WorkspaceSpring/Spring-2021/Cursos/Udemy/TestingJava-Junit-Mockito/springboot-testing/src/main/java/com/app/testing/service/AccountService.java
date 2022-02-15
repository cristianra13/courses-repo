package com.app.testing.service;

import com.app.testing.models.Account;

import java.math.BigDecimal;

public interface AccountService {
    Account findById(Long id);

    int reviewTotalTranfers(Long bankId);

    BigDecimal reviewBalance(Long accountId);

    void tranfer(Long accountOrigin, Long accountDest, BigDecimal amount, Long bankId);
}
