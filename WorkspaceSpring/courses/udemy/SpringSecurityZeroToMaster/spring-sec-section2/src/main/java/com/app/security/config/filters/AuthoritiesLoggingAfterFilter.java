package com.app.security.config.filters;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import java.io.IOException;
import java.util.logging.Logger;

public class AuthoritiesLoggingAfterFilter implements Filter {

  private final Logger log = Logger.getLogger(this.getClass().getName());

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
    throws IOException, ServletException {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (null != authentication) {
      log.info("User " + authentication.getName() + " id successfully authenticate and "
        + "has the authorities " + authentication.getAuthorities().toString());
    }
    chain.doFilter(request, response);
  }
}
