package com.app.test.security.config;

import com.app.test.security.dto.UserLoginDto;
import com.app.test.security.repository.UserLoginDtoRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * La implementación de UserDetailsService, nos permitirá cargar datos especificos del usuario
 */

@Service
public class UserDetailServiceImpl implements UserDetailsService
{
    private UserLoginDtoRepository repository;

    public UserDetailServiceImpl(UserLoginDtoRepository repository)
    {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        UserLoginDto userLoginDto = repository.findByUsername(username);
        if(userLoginDto == null)
        {
            throw new UsernameNotFoundException(username);
        }

        // enviamos los authorities como lista vacía
        return new User(userLoginDto.getUsername(), userLoginDto.getPassword(), Collections.emptyList());
    }
}
