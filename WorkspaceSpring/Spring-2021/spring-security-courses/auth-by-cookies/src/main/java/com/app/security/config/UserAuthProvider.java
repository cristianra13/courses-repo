package com.app.security.config;

import com.app.security.dto.CredentialsDto;
import com.app.security.dto.UserDto;
import com.app.security.service.AuthenticationService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.util.Collections;

public class UserAuthProvider implements AuthenticationProvider
{
    private final AuthenticationService authenticationService;

    public UserAuthProvider(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException
    {
        UserDto userDto = null;

        if(authentication instanceof UsernamePasswordAuthenticationToken)
        {
            // autenticación por usuario y password
            userDto = authenticationService.authenticate(new CredentialsDto(
                    (String) authentication.getPrincipal(),
                    (char[]) authentication.getCredentials()
            ));
        }
        else if (authentication instanceof PreAuthenticatedAuthenticationToken)
        {
            // autenticacion por cookies
            authenticationService.findByToken((String) authentication.getPrincipal());
        }

        if(userDto == null)
        {
            return null;
        }

        return new UsernamePasswordAuthenticationToken(userDto, null, Collections.emptyList());
    }

    @Override
    public boolean supports(Class<?> authentication)
    {
        return true;
    }
}
