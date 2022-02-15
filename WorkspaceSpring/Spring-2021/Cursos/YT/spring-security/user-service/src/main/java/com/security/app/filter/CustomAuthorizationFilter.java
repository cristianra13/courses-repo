package com.security.app.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.*;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
public class CustomAuthorizationFilter extends OncePerRequestFilter {

  private static final String BEARER = "Bearer ";

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    if (request.getServletPath().equals("/api/login")) {
      filterChain.doFilter(request, response);
    } else {
      String authorizationheader = request.getHeader(AUTHORIZATION);
      if (authorizationheader != null && authorizationheader.startsWith(BEARER)) {
        try {
          String token = authorizationheader.substring(BEARER.length());
          Algorithm algorithm = Algorithm.HMAC256("my-secret".getBytes());
          JWTVerifier verifier = JWT.require(algorithm).build();
          DecodedJWT decodedJWT = verifier.verify(token);
          String username = decodedJWT.getSubject();
          String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
          Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
          stream(roles).forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));
          UsernamePasswordAuthenticationToken authenticationToken =
              new UsernamePasswordAuthenticationToken(username, null, authorities);
          SecurityContextHolder.getContext().setAuthentication(authenticationToken);

          filterChain.doFilter(request, response);
        } catch (Exception e) {
          log.error("Error logging in: {}", e.getMessage());
          response.setHeader("error", e.getMessage());
          response.setStatus(FORBIDDEN.value());
          // response.sendError(FORBIDDEN.value());
          Map<String, String> errors = new HashMap<>();
          errors.put("error_message", e.getMessage());
          response.setContentType(APPLICATION_JSON_VALUE);
          // Agregamos el mapa de tokens al response body
          new ObjectMapper().writeValue(response.getOutputStream(), errors);
        }
      } else {
        filterChain.doFilter(request, response);
      }
    }
  }
}
