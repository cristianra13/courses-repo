package com.app.security.controller;

import com.app.security.model.Customer;
import com.app.security.model.Loans;
import com.app.security.repository.LoansRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoansController {

  @Autowired
  private LoansRepository loansRepository;

  @PostMapping("myLoans")
  public List<Loans> getLoanDetails(@RequestBody Customer customer) {
    List<Loans> loans = loansRepository.findByCustomerIdOrderByStartDtDesc(customer.getId());
    if (loans != null) {
      return loans;
    }
    return null;
  }

}
