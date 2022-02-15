package com.example.auth;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.example.security.ApplicationUserRole.*;

/**
 * Clase que actua como repository fake para buscar los usuarios
 * @author PRREDATOR
 *
 */
@Repository("fake")
public class FakeApplicationUserDaoService implements ApplicationUserDao
{
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public FakeApplicationUserDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationuserByUsername(String username)
    {
        return getApplicationUsers()
                .stream().filter(user -> username.equals(user.getUsername()))
                .findFirst();
    }

    private List<ApplicationUser> getApplicationUsers()
    {
        List<ApplicationUser> applicationUsers = Lists.newArrayList(
                new ApplicationUser(STUDENT.getGrantedAuthorities(), "cristian", passwordEncoder.encode("123"), true, true, true, true),
                new ApplicationUser(ADMIN.getGrantedAuthorities(), "maria", passwordEncoder.encode("123"), true, true, true, true),
                new ApplicationUser(ADMINTRAINEE.getGrantedAuthorities(), "tom", passwordEncoder.encode("123"), true, true, true, true)
        );
        return applicationUsers;
    }
}
