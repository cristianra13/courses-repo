package com.app.test.security.repository;

import com.app.test.security.dto.UserLoginDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLoginDtoRepository extends JpaRepository<UserLoginDto, Long>
{
    UserLoginDto findByUsername(String username);
}
