package com.example.auth;

import com.example.entities.UserLogin;
import com.example.repository.UserRepository;
import com.example.security.ApplicationUserRole;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService implements UserDetailsService
{
    private UserRepository userRepository;

    public ApplicationUserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        UserLogin userLogin = userRepository.findByUsername(username);
        if(userLogin == null)
        {
            throw new UsernameNotFoundException(String.format("Username  %s not found", username));
        }
        ApplicationUser applicationUser = new ApplicationUser(ApplicationUserRole.STUDENT.getGrantedAuthorities(), userLogin.getUsername(), userLogin.getPassword(), true, true, true, userLogin.isActive());

        return applicationUser;
    }
}
