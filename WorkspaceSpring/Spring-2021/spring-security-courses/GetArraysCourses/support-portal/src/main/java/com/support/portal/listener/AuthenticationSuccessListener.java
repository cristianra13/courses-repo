package com.support.portal.listener;

import com.support.portal.domain.UserPrincipal;
import org.springframework.stereotype.Component;
import com.support.portal.service.LoginAttemptService;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;

@Component
public class AuthenticationSuccessListener {
    private LoginAttemptService loginAttemptService;

    public AuthenticationSuccessListener(LoginAttemptService loginAttemptService) {
        this.loginAttemptService = loginAttemptService;
    }

    /**
     * Método encargado de crear un listener y estár
     * pendiente de un login exitoso, y así, eliminar
     * del cache el maximo de intentos del usuario
     *
     * @param event
     */
    @EventListener
    public void onAuthenticationSuccess(AuthenticationSuccessEvent event) {
        Object principal = event.getAuthentication().getPrincipal();
        if (principal instanceof UserPrincipal) {
            UserPrincipal user = (UserPrincipal) event.getAuthentication().getPrincipal();
            loginAttemptService.evictUserFromLoginAttemptCached(user.getUsername());
        }
    }
}
