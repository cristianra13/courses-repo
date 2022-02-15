package com.app.security.service;

import com.app.security.model.Customer;
import com.app.security.model.SecurityCustomer;
import com.app.security.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EazyBankUserDetailsService implements UserDetailsService {

  @Autowired
  private CustomerRepository customerRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    // buscamos el usuario por email
    List<Customer> customers = customerRepository.findByEmail(username);
    if (customers.size() == 0) {
      throw  new UsernameNotFoundException("user details not found for the user: " + username);
    }
    // enviamos al constructor el primer usuario encontrado
    return new SecurityCustomer(customers.get(0));
  }
}
