package com.example.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class JwtUsernamePasswordAuthFilter extends UsernamePasswordAuthenticationFilter
{
    private final AuthenticationManager authenticationManager;

    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;

    public JwtUsernamePasswordAuthFilter(AuthenticationManager authenticationManager, JwtConfig jwtConfig, SecretKey secretKey) {
        this.authenticationManager = authenticationManager;
        this.jwtConfig = jwtConfig;
        this.secretKey = secretKey;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException
    {
        try
        {
            UsernamePasswordAuthRequest authRequest = new ObjectMapper().readValue(request.getInputStream(), UsernamePasswordAuthRequest.class);
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    authRequest.getUsername(),
                    authRequest.getPassword()
            );
            Authentication authenticate = authenticationManager.authenticate(authentication);
            return authenticate;
        }
        catch(IOException io)
        {
            throw new RuntimeException(io);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException
    {
        Date now = new Date();
        String jwtToken = Jwts.builder()
                .setSubject(authResult.getName())
                .claim("authorities", authResult.getAuthorities())
                .claim("isActive", authResult.getPrincipal())
                .setIssuedAt(new Date())
                //.setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays((long) 0.1)))
                //.setExpiration(new Date(now.getTime() + 10 * 60 * 1000)) // token expire in 10 minutes
                .setExpiration(new Date(now.getTime() + jwtConfig.getTokenExpirationAfterMinutes() * 60 * 1000)) // token expire in 10 minutes
                .signWith(secretKey)
                .compact();

        response.addHeader(jwtConfig.getAuthorizationHeader(), jwtConfig.getTokenPrefix() + jwtToken);
    }
}
