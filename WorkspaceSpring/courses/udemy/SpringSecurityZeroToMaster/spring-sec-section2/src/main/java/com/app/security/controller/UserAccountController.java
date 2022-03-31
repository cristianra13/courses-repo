package com.app.security.controller;

import com.app.security.model.Account;
import com.app.security.model.Customer;
import com.app.security.repository.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAccountController {

  @Autowired
  private AccountsRepository accountsRepository;

  @PostMapping("/myAccount")
  public Account getAccountDetails(@RequestBody Customer customer) {
    return accountsRepository.findByCustomerId(customer.getId());
  }
}
