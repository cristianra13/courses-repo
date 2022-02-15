package com.sergio.socialnetwork.config;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtAuthFilter extends OncePerRequestFilter {

    private final UserAuthenticationProvider userAuthenticationProvider;

    public JwtAuthFilter(UserAuthenticationProvider userAuthenticationProvider) {
        this.userAuthenticationProvider = userAuthenticationProvider;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            FilterChain filterChain) throws ServletException, IOException {

        // el token debe llegar en el encabezado
        String header = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);

        if (header != null) {
            // validamos si viene la parte del Bearer del token
            String[] authElements = header.split(" ");

            if (authElements.length == 2 && "Bearer".equals(authElements[0])) {
                /*
                    llamamos al servicio de autenticaicón para validar el token
                    y me devuelve un objeto de autenticación para agregar al contexto de seguridad
                 */
                try {
                    SecurityContextHolder.getContext().setAuthentication(
                            userAuthenticationProvider.validateToken(authElements[1]));
                } catch (RuntimeException e) {
                    SecurityContextHolder.clearContext();
                    throw e;
                }
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
