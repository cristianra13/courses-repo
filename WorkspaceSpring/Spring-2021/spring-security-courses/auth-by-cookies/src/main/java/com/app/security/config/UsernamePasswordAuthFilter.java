package com.app.security.config;

import com.app.security.dto.CredentialsDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UsernamePasswordAuthFilter extends OncePerRequestFilter
{
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final String PATH = "/v1/signIn";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException
    {
        if(PATH.equals(request.getServletPath()) && HttpMethod.POST.matches(request.getMethod()))
        {
            CredentialsDto credentialsDto = MAPPER.readValue(request.getInputStream(), CredentialsDto.class);
            SecurityContextHolder.getContext().setAuthentication( new UsernamePasswordAuthenticationToken(
                credentialsDto.getLogin(),
                credentialsDto.getPassword())
            );
        }
        filterChain.doFilter(request, response);
    }
}
