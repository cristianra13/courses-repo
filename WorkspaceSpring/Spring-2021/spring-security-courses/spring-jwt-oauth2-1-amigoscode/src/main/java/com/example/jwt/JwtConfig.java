package com.example.jwt;

import com.google.common.net.HttpHeaders;
import io.jsonwebtoken.security.Keys;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Data
@Component
@ConfigurationProperties(prefix = "application.jwt")
public class JwtConfig
{
    private String secretKey;
    private String tokenPrefix;
    private Integer tokenExpirationAfterMinutes;


    public String getAuthorizationHeader()
    {
        return HttpHeaders.AUTHORIZATION;
    }
}
