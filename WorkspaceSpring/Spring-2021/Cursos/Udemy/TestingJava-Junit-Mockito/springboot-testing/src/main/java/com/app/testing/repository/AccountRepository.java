package com.app.testing.repository;

import com.app.testing.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    // @Query("select p from Account p where p.person = ?1")
    Optional<Account> findByPerson(String person);
}
