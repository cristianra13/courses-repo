package com.app.test.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * Creamos un bean para que este se guarde en el contexto de Spring
 */
@Configuration
public class PasswordEncoderConfig
{
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder(10);
    }
}
