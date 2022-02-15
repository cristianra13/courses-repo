package com.app.test.security.config.jwt;

import com.app.test.security.config.SecurityConstants;
import com.app.test.security.dto.LoginResponseDto;
import com.app.test.security.dto.UserLoginDto;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import static org.springframework.http.HttpStatus.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import com.auth0.jwt.JWT;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter
{
    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager)
    {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException
    {
        try
        {
            // Obtenemos los datos entrantes y los asignamos a la clase UserLoginDto
            UserLoginDto userLoginDto = new ObjectMapper().readValue(request.getInputStream(), UserLoginDto.class);

            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    userLoginDto.getUsername(),
                    userLoginDto.getPassword(),
                    new ArrayList<>() // estos son los authorities
            ));
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo que se ejcuta en caso de que la autenticación sea exitosa
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException, ServletException
    {

        String token = JWT.create()
                .withSubject(((User)authResult.getPrincipal()).getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()))
                .sign(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes()));

        response.addHeader(HttpHeaders.AUTHORIZATION, SecurityConstants.TOKEN_PREFIX + token);

        LoginResponseDto responseDto = new LoginResponseDto();
        responseDto.setToken(SecurityConstants.TOKEN_PREFIX + token);
        responseDto.setError(false);
        responseDto.setMessage(OK.getReasonPhrase());
        responseDto.setStatus(OK.value());
        response.getWriter().write(new ObjectMapper().writeValueAsString(responseDto));
        response.setContentType("application/json");

    }

    /**
     * Metodo que se ejcuta en caso de que la autenticación sea fallida
     *
     * @param request
     * @param response
     * @param failed
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed)
            throws IOException, ServletException
    {
        LoginResponseDto responseDto = new LoginResponseDto();
        responseDto.setError(true);
        responseDto.setMessage("Error de credenciales: usuario o contraseña incorrectos");
        responseDto.setReason(failed.getMessage());
        responseDto.setStatus(UNAUTHORIZED.value());

        response.getWriter().write(new ObjectMapper().writeValueAsString(responseDto));
        response.setStatus(401);
        response.setContentType("application/json");
    }
}
