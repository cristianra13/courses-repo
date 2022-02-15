package com.app.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableResourceServer
public class AuthorizattionServerOauth2Application {

    public static void main(String[] args) {
        SpringApplication.run(AuthorizattionServerOauth2Application.class, args);
    }

}
