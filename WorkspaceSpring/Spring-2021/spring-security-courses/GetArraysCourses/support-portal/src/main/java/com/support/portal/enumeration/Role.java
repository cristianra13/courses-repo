package com.support.portal.enumeration;

import static com.support.portal.constant.Authority.*;

/**
 * Enum contenedora de los roles del usuario
 * adicionando los permisos del mismo, obtenidos
 * de Authority.class
 */
public enum Role {
    ROLE_USER(USER_AUTHORITIES),
    ROLE_HHRR(HHRR_AUTHORITIES),
    ROLE_MANAGER(MANAGER_AUTHORITIES),
    ROLE_ADMIN(ADMIN_AUTHORITIES),
    ROLE_SUPER_ADMIN(SUPER_ADMIN_AUTHORITIES);

    private String[] authorities;

    Role(String... authorities) {
        this.authorities = authorities;
    }

    public String[] getAuthorities(){
        return this.authorities;
    }
}
