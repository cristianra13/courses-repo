package com.support.portal.repository;

import com.support.portal.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User findByEmail(String email);
}
