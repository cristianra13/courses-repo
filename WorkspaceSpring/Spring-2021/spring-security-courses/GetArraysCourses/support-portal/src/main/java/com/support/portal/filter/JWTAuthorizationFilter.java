package com.support.portal.filter;

import static com.support.portal.constant.SecurityConstant.*;

import com.support.portal.exception.domain.TokenNullException;
import com.support.portal.utility.JWTTokenProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    private JWTTokenProvider tokenProvider;

    public JWTAuthorizationFilter(JWTTokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        if (httpServletRequest.getMethod().equals(OPTIONS_HTTP_METHOD)) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
        } else {
            String authorizationHeader = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
            if (authorizationHeader == null || !authorizationHeader.startsWith(TOKEN_PREFIX)) {
                filterChain.doFilter(httpServletRequest, httpServletResponse);
                return;
            }
            String token = authorizationHeader.substring(TOKEN_PREFIX.length());
            if(token == null){
                filterChain.doFilter(httpServletRequest, httpServletResponse);
            }
            String username = tokenProvider.getSubject(token);
            if (tokenProvider.isTokenValid(username, token) &&
                    SecurityContextHolder.getContext().getAuthentication() == null) {
                List<GrantedAuthority> authorities = tokenProvider.getAuthorities(token);
                Authentication authentication = tokenProvider.getAuthentication(username, authorities, httpServletRequest);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                SecurityContextHolder.clearContext();
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
