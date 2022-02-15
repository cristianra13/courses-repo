package com.app.security.controller;

import com.app.security.model.Customer;
import com.app.security.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
public class LoginController {

  @Autowired
  private CustomerRepository customerRepository;

  @RequestMapping("/user")
  public Customer getuserDetailsAfterLogin(Principal user) {
    List<Customer> customers = customerRepository.findByEmail(user.getName());
    if (customers.size() > 0) {
      return customers.get(0);
    }
    return null;
  }
}
