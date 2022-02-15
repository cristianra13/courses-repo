package com.app.security.repository;

import java.util.List;

import com.app.security.model.AccountTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountTransactionsRepository extends JpaRepository<AccountTransaction, Long> {

  List<AccountTransaction> findByCustomerIdOrderByTransactionDtDesc(int customerId);

}
