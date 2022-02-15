package com.app.social.network.config;

import com.app.social.network.dto.CredentialsDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  Dedicado a la autenticaicòn basica por username y password
 */

public class UsernamePasswordAuthFilter extends OncePerRequestFilter {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private final UserAuthenticationProvider userAuthenticationProvider;

    public UsernamePasswordAuthFilter(UserAuthenticationProvider userAuthenticationProvider) {
        this.userAuthenticationProvider = userAuthenticationProvider;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException
    {
        /*
            Filtro que se usara solo para la url de singIn
         */
        if("/v1/singIn".equals(request.getServletPath()) && HttpMethod.POST.matches(request.getMethod()))
        {
            // leemos la información de las credenciales de llamada
            CredentialsDto credentialsDto = MAPPER.readValue(request.getInputStream(), CredentialsDto.class);
            try {
                SecurityContextHolder.getContext().setAuthentication(userAuthenticationProvider.validateCredentials(credentialsDto));
            } catch (RuntimeException e) {
                // borramos contexto de seguridad si ocurre algún error
                SecurityContextHolder.clearContext();
                throw e;
            }
        }
        filterChain.doFilter(request, response);
    }
}
