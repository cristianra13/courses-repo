package com.security.app;

import com.security.app.domain.models.Role;
import com.security.app.domain.models.User;
import com.security.app.domain.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class UserServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(UserServiceApplication.class, args);
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  CommandLineRunner run(UserService userService) {
    return args -> {
      userService.saveRole(new Role(null, "ROLE_USER"));
      userService.saveRole(new Role(null, "ROLE_MANAGER"));
      userService.saveRole(new Role(null, "ROLE_ADMIN"));
      userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

      userService.saveUser(new User(null, "John Travolta", "travolta", "password", new ArrayList<>()));
      userService.saveUser(new User(null, "Jim Carry", "carry", "password", new ArrayList<>()));
      userService.saveUser(new User(null, "Will Smith", "smith", "password", new ArrayList<>()));
      userService.saveUser(new User(null, "Helen Skindou", "Helen", "password", new ArrayList<>()));

      userService.addRoleToUser("travolta", "ROLE_USER");
      userService.addRoleToUser("travolta", "ROLE_MANAGER");
      userService.addRoleToUser("smith", "ROLE_MANAGER");
      userService.addRoleToUser("carry", "ROLE_ADMIN");
      userService.addRoleToUser("helen", "ROLE_SUPER_ADMIN");
      userService.addRoleToUser("helen", "ROLE_ADMIN");
      userService.addRoleToUser("helen", "ROLE_USER");
    };
  }

}
