package com.app.springboot.testing.repository;

import com.app.springboot.testing.domain.models.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank, Long> { }
