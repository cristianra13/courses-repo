package com.app.security.config;


import com.app.security.config.filters.AuthoritiesLoggingAfterFilter;
import com.app.security.config.filters.AuthoritiesLoggingAtFilter;
import com.app.security.config.filters.JWTTokenGeneratorFilter;
import com.app.security.config.filters.JWTTokenValidatorFilter;
import com.app.security.config.filters.RequestValidationBeforeFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // securizamos nuestros endpoints
    http
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      .and()
      .cors().configurationSource(new CorsConfigurationSource() {
      // Definimos los cors permitidos
          @Override
          public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
            CorsConfiguration configuration = new CorsConfiguration();
            configuration.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
            configuration.setAllowedMethods(Collections.singletonList("*"));
            configuration.setAllowCredentials(true);
            configuration.setAllowedHeaders(Collections.singletonList("*"));
            configuration.setExposedHeaders(List.of("Authorization"));
            configuration.setMaxAge(3600L);
            return configuration;
          }
        })
        .and()
        //.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
        .csrf().disable()
        //.ignoringAntMatchers("/contact").csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
        //.and()
        .addFilterBefore(new RequestValidationBeforeFilter(), BasicAuthenticationFilter.class)
        .addFilterAfter(new AuthoritiesLoggingAfterFilter(), BasicAuthenticationFilter.class)
        .addFilterBefore(new JWTTokenValidatorFilter(), BasicAuthenticationFilter.class)
        .addFilterAfter(new JWTTokenGeneratorFilter(), BasicAuthenticationFilter.class)
        .addFilterAt(new AuthoritiesLoggingAtFilter(), BasicAuthenticationFilter.class)
        .authorizeRequests()
        .antMatchers("/myAccount").hasRole("USER")
        .antMatchers("/myBalance").hasAnyRole("USER", "ADMIN")
        .antMatchers("/myLoans").authenticated()
        .antMatchers("/myCards").authenticated()
        .antMatchers("/user").authenticated()
        .antMatchers("/notices").permitAll() // No require autenticación
        .antMatchers("/contact").permitAll()
        //.and()
        //.formLogin()
        .and()
        .httpBasic();

    /**
     * Configuration to deny all request
     */
//    http.authorizeRequests().anyRequest().denyAll()
//        .and().formLogin().and().httpBasic();

    /**
     * Configuration to permit all request
     */
//    http.authorizeRequests().anyRequest().permitAll()
//        .and().formLogin().and().httpBasic();
  }

  /*@Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    // Users in memory
    auth.inMemoryAuthentication()
        .withUser("cristian")
        .password("123456")
        .authorities("admin")
        .and()
        .withUser("user")
        .password("user")
        .authorities("read")
        .and()
        .passwordEncoder(NoOpPasswordEncoder.getInstance());
  }*/

//  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//    // Users in memory
//    // Two way to build user
//    InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
//    UserDetails user1 = User.withUsername("admin").password("12345").authorities("admin").build();
//    User.UserBuilder user2 = User.withUsername("user").password("12345").authorities("read");
//
//    userDetailsManager.createUser(user1);
//    userDetailsManager.createUser(user2.build());
//
//    auth.userDetailsService(userDetailsManager);
//  }


//  @Bean
//  public UserDetailsService userDetailsService(DataSource dataSource) {
//    return new JdbcUserDetailsManager(dataSource);
//  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
