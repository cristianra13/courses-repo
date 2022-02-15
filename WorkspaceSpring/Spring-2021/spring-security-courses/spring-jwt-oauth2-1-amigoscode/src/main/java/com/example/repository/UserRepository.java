package com.example.repository;

import com.example.entities.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<UserLogin, Long>
{
    UserLogin findByUsername(String username);
}
