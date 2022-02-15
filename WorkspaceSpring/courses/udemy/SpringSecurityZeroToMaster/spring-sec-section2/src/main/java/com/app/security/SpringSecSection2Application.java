package com.app.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScans({
    @ComponentScan("com.app.security.controller"),
    @ComponentScan("com.app.security.config")})
@EnableJpaRepositories("com.app.security.repository")
@EntityScan("com.app.security.model")
public class SpringSecSection2Application {

  public static void main(String[] args) {
    SpringApplication.run(SpringSecSection2Application.class, args);
  }

}
