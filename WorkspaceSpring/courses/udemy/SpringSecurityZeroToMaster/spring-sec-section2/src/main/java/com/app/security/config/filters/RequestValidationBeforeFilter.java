package com.app.security.config.filters;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.springframework.http.HttpHeaders.*;

public class RequestValidationBeforeFilter implements Filter {

  private static final String AUTHENTICATION_SCHEMA_BASIC = "basic";
  private Charset credentialsCharset = StandardCharsets.UTF_8;

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
    HttpServletRequest servletRequest = (HttpServletRequest) request;
    HttpServletResponse servletResponse = (HttpServletResponse) response;
    String header = servletRequest.getHeader(AUTHORIZATION);
    if (header != null) {
      header = header.trim();
      if (StringUtils.startsWithIgnoreCase(header, AUTHENTICATION_SCHEMA_BASIC)) {
        byte[] base64Token = header.substring(6).getBytes(StandardCharsets.UTF_8);
        byte[] decoed;
        try {
          decoed = Base64.getDecoder().decode(base64Token);
          String token = new String(decoed, getCredentialsCharset(servletRequest));
          int delim = token.indexOf(":");
          if (delim == -1) {
            throw new BadCredentialsException("Invalida basic authentication token");
          }
          String email = token.substring(0, delim);
          if (email.toLowerCase().contains("test")) {
            servletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
          }
        } catch (IllegalArgumentException e) {
          throw new BadCredentialsException("Failed to decode basic authentication token");
        }
      }
    }
    filterChain.doFilter(request, response);
  }

  protected Charset getCredentialsCharset(HttpServletRequest request) {
    return getCredentialsCharset();
  }

  public Charset getCredentialsCharset() {
    return this.credentialsCharset;
  }

}
