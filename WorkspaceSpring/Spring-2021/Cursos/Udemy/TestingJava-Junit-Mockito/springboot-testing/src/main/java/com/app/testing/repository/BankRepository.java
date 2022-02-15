package com.app.testing.repository;

import com.app.testing.models.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank, Long> { }
