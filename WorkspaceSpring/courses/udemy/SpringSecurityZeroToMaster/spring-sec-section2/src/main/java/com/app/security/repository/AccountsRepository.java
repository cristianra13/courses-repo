package com.app.security.repository;

import com.app.security.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepository extends JpaRepository<Account, Long> {

	Account findByCustomerId(int customerId);
}
