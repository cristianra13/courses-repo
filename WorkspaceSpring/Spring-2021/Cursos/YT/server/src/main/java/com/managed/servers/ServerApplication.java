package com.managed.servers;

import com.managed.servers.enumeration.Status;
import com.managed.servers.models.Server;
import com.managed.servers.repository.ServerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

import static com.managed.servers.enumeration.Status.*;

@SpringBootApplication
public class ServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(ServerApplication.class, args);
  }

  @Bean
  CommandLineRunner run(ServerRepository serverRepository) {
    return args -> {
      serverRepository.save(
          new Server(null,
              "172.17.0.1",
              "Ubuntu Linux",
              "32 GB",
              "Personal Computer",
              "http://localhost:80807server/image/server1.png",
              SERVER_UP));

      serverRepository.save(
          new Server(null,
              "192.168.1.185",
              "Fedora Linux",
              "16 GB",
              "Web Server",
              "http://localhost:80807server/image/server2.png",
              SERVER_UP));

      serverRepository.save(
          new Server(null,
              "192.168.0.1",
              "Cent OS Prod",
              "64 GB",
              "Production Server",
              "http://localhost:80807server/image/server3.png",
              SERVER_UP));

      serverRepository.save(
          new Server(null,
              "192.168.0.10",
              "MS Server",
              "16 GB",
              "Mail Server",
              "http://localhost:80807server/image/server4.png",
              SERVER_UP));

    };
  }

  @Bean
  public CorsFilter corsFilter() {
    UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
    CorsConfiguration corsConfiguration = new CorsConfiguration();
    corsConfiguration.setAllowCredentials(true);
    corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://localhost:4200"));
    corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
        "Accept", "Jwt-Token", "Authorization", "Origin, Accept", "X-Requested-With", "Access-Control-Request-Method",
        "Access-Control-Request-Headers"));
    corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Jwt-Token", "Authorization",
        "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials", "Filename"));
    corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
    urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);

    return new CorsFilter(urlBasedCorsConfigurationSource);
  }

}
