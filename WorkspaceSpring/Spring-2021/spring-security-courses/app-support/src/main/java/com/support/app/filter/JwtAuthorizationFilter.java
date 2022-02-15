package com.support.app.filter;

import com.support.app.constants.SecurityConstants;
import com.support.app.utility.JWTTokenProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends OncePerRequestFilter
{

    private JWTTokenProvider tokenProvider;

    public JwtAuthorizationFilter(JWTTokenProvider tokenProvider)
    {
        this.tokenProvider = tokenProvider;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException
    {
        if(request.getMethod().equalsIgnoreCase(SecurityConstants.OPTIONS_HTTP_METHOD))
        {
             response.setStatus(HttpStatus.OK.value());
        }
        else
        {
            String authorizedHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
            if(authorizedHeader == null || authorizedHeader.startsWith(SecurityConstants.TOKEN_HEADER))
            {
                filterChain.doFilter(request, response);
                return;
            }
        }
    }
}
