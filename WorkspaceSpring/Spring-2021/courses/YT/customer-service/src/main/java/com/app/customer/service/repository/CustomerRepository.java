package com.app.customer.service.repository;

import com.app.customer.service.domain.entities.Customer;
import com.app.customer.service.domain.entities.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

  Customer findByNumberID(String numberID);

  List<Customer> findByLastName(String lastName);

  List<Customer> findByRegion(Region region);
}