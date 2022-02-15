package com.app.social.network.config;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthFilter extends OncePerRequestFilter {

    private final String BEARER = "Bearer";
    private final UserAuthenticationProvider userAuthenticationProvider;

    public JwtAuthFilter(UserAuthenticationProvider userAuthenticationProvider) {
        this.userAuthenticationProvider = userAuthenticationProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException
    {
        // el token debe llegar en el encabezado
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(header != null)
        {
            String[] authElements =  header.split(" ");
            // validamos si viene la parte del Bearer del token
            if(authElements.length == 2 && BEARER.equals(authElements[0]))
            {
                /*
                    llamamos al servicio de autenticaicón para validar el token
                    y me devuelve un objeto de autenticación para agregar al contexto de seguridad
                 */
                try {
                    SecurityContextHolder.getContext().setAuthentication(userAuthenticationProvider.validateToken(authElements[1]));
                } catch (RuntimeException e) {
                    SecurityContextHolder.clearContext();
                    throw e;
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
