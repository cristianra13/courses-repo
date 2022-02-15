package com.app.springboot.testing.data;

import com.app.springboot.testing.domain.models.Account;
import com.app.springboot.testing.domain.models.Bank;

import java.math.BigDecimal;
import java.util.Optional;

public class Data {

    public static final Bank bank001 = new Bank(1L, "Banco Nacional", 0);
    public static final Bank bank002 = new Bank(2L, "Banco Internacional", 0);

    public static Optional<Account> createAccount001() {
        return Optional.of( new Account(1L, "Cristian Real", new BigDecimal("1000")));
    }

    public static Optional<Account> createAccount002() {
        return Optional.of( new Account(2L, "John Doe", new BigDecimal("3000")));
    }

    public static Optional<Account> createAccount003() {
        return Optional.of( new Account(3L, "Emma Smith", new BigDecimal("3500")));
    }

    public static Optional<Account> createAccount004() {
        return Optional.of( new Account(4L, "Maria Diaz", new BigDecimal("500")));
    }

    public static Optional<Bank> createBank001() {
        return Optional.of(new Bank(1L, "Banco Nacional", 0));
    }

    public static Optional<Bank> createBank002() {
        return Optional.of( new Bank(2L, "Banco Internacional", 0));
    }
}
