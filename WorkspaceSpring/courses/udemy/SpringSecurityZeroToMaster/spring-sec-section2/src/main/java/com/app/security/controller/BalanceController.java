package com.app.security.controller;

import com.app.security.model.AccountTransaction;
import com.app.security.model.Customer;
import com.app.security.repository.AccountTransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BalanceController {

  @Autowired
  private AccountTransactionsRepository accountTransactionsRepository;

  @PostMapping("/myBalance")
  public List<AccountTransaction> getBalanceDetails(@RequestBody Customer customer) {
    List<AccountTransaction> accountTransactions = accountTransactionsRepository.
        findByCustomerIdOrderByTransactionDtDesc(customer.getId());
    if (accountTransactions != null ) {
      return accountTransactions;
    }else {
      return null;
    }
  }
}
