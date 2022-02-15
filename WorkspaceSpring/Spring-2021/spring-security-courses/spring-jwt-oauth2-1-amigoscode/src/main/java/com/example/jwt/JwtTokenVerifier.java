package com.example.jwt;

import com.google.common.base.Strings;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class JwtTokenVerifier extends OncePerRequestFilter
{

    private final SecretKey secretKey;
    private final JwtConfig jwtConfig;

    public JwtTokenVerifier(SecretKey secretKey, JwtConfig jwtConfig) {
        this.secretKey = secretKey;
        this.jwtConfig = jwtConfig;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException
    {
        // obtenemos del header el Bearer token
        String authorizationHeader = httpServletRequest.getHeader(jwtConfig.getAuthorizationHeader());

        if(Strings.isNullOrEmpty(authorizationHeader) || !authorizationHeader.startsWith(jwtConfig.getTokenPrefix()))
        {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        String token = authorizationHeader.replace(jwtConfig.getTokenPrefix(), "");
        try
        {
            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);

            Claims body = claimsJws.getBody();
            String username = body.getSubject();
            List<Map<String, String>> authorities = (List<Map<String, String>>) body.get("authorities") ;

            // tomamos los authorities y los convertimos en un objeto Set<SimpleGrantedAuthority>
            Set<SimpleGrantedAuthority> simpleGrantedAuthorities = authorities.stream().map(mapa -> new SimpleGrantedAuthority(mapa.get("authority")))
                    .collect(Collectors.toSet());

            Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, simpleGrantedAuthorities);

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        catch (JwtException e)
        {
            // si el token no es valido
            throw new IllegalStateException(String.format("Token %s cannot be truest", token));
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
