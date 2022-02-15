package com.sergio.socialnetwork.config;

import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import javax.annotation.PostConstruct;

import com.sergio.socialnetwork.dto.CredentialsDto;
import com.sergio.socialnetwork.dto.UserDto;
import com.sergio.socialnetwork.services.AuthenticationService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class UserAuthenticationProvider {

    /*
        Clave para construir el JWT
     */
    @Value("${security.jwt.token.secret-key:secret-key}")
    private String secretKey;

    private final AuthenticationService authenticationService;

    public UserAuthenticationProvider(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostConstruct
    protected void init() {
        // se codifica en base64 para no tenerla visible para la JVM
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    /*
        Creación del token, el cual está dividido en tres partes:
        encabezado: contiene la información sobre el algoritmo utilizado para cifrar el token y que tipo de token es
        payload: con las Claims que son la información que puedo encontrar en el token. como datos del usuario, tiempo de
            vencimiento, fecha de creación y más.
        firma: para la firma se usa el contenido del encabezado, codificado en base64 luego el contenido de la carga,
            también codificado en base64 y cifrar la concatenación de ambos usando el algoritmo especificado en el encabezado

        resultado del token: el encabezado cifrado en base64, seguido de un punto la carga o contenido codificada en base64 seguido de un punto, y la firma
     */
    public String createToken(String login) {
        Claims claims = Jwts.claims().setSubject(login);

        Date now = new Date();
        Date validity = new Date(now.getTime() + 3600000); // 1 hour

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    /*
        Metodo para validar el token o si al
     */
    public Authentication validateToken(String token) {
        String login = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

        UserDto user = authenticationService.findByLogin(login);

        return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
    }

    // Autenticacion para usuario y contraseña
    public Authentication validateCredentials(CredentialsDto credentialsDto) {
        UserDto user = authenticationService.authenticate(credentialsDto);
        return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
    }
}
