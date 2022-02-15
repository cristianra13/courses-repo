package com.support.portal.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import static java.util.Arrays.stream;
import java.util.Collection;
import java.util.stream.Collectors;

public class UserPrincipal implements UserDetails {

    private User user;

    public UserPrincipal(User user) {
        this.user = user;
    }

    /**
     * Pasamos el arreglo a un stream y hacemos un map por cada permiso,
     * pasandolo a un SimpleGrantedAuthority, y al final recolectamos todos en una lista
     *
     * @return Collection<? extends GrantedAuthority>
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return stream(this.user.getAuthorities()).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.user.isNotLockedUser();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.user.isActiveUser();
    }
}
