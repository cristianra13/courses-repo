package com.example.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService implements UserDetailsService
{
    private final ApplicationUserDao dao;

    /*
        Injection by constructor
     */
    @Autowired
    public ApplicationUserService(@Qualifier("fake") ApplicationUserDao dao)
    {
        this.dao = dao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        return dao.selectApplicationuserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username  %s not found", username)));
    }
}
