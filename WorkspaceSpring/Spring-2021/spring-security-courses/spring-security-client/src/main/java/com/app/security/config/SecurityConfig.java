package com.app.security.config;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final ObjectMapper mapper;

    public SecurityConfig(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/oauth2/**", "/login**").permitAll()
                .anyRequest().authenticated()
        .and()
                .oauth2Login()
        .authorizationEndpoint()
        .authorizationRequestRepository(new InMemoryRequestRepository())
        .and()
        .successHandler(this::successHandler)
        .and()
        .exceptionHandling()
        .authenticationEntryPoint(this::authenticationEntryPoint)
        ;
    }

    private void successHandler(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
    	
    }

    private void authenticationEntryPoint(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(mapper.writeValueAsString(Collections.singletonMap("error", "Unauthenticated"));
    }
}
