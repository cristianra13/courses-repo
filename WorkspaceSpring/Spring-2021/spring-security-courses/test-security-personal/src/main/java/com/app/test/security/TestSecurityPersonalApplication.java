package com.app.test.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class TestSecurityPersonalApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(TestSecurityPersonalApplication.class, args);
    }
}
