package com.app.security.repository;

import com.app.security.model.Loans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface LoansRepository extends JpaRepository<Loans, Long> {

  List<Loans> findByCustomerIdOrderByStartDtDesc(int customerId);
}
