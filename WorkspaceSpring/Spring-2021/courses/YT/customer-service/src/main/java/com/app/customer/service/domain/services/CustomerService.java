package com.app.customer.service.domain.services;

import com.app.customer.service.domain.entities.Customer;
import com.app.customer.service.domain.entities.Region;

import java.util.List;

public interface CustomerService {

  List<Customer> findCustomerAll();
  List<Customer> findCustomersByRegion(Region region);

  Customer createCustomer(Customer customer);

  Customer updateCustomer(Customer customer);

  Customer deleteCustomer(Customer customer);

  Customer getCustomer(Long id);
}
