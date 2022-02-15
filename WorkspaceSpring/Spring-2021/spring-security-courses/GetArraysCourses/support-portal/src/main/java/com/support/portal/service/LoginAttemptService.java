package com.support.portal.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

import static java.util.concurrent.TimeUnit.MINUTES;

@Service
public class LoginAttemptService {
    private static final int MAX_NUMBER_OF_ATTEMPTS = 5;
    private static final int ATTEMPTS_INCREMENT = 1;
    private LoadingCache<String, Integer> loginAttemptCached;

    public LoginAttemptService() {
        super();
        this.loginAttemptCached = CacheBuilder
                .newBuilder()
                .expireAfterWrite(15, MINUTES)
                .maximumSize(100)
                .build(new CacheLoader<String, Integer>() {
                    @Override
                    public Integer load(String key) throws Exception {
                        return 0;
                    }
                });
    }

    /**
     * Elimina del cache los intentos del usuario
     *
     * @param username
     */
    public void evictUserFromLoginAttemptCached(String username) {
        loginAttemptCached.invalidate(username);
    }

    /**
     * Agrega al cache el intento del usuario (username)
     *
     * @param username
     */
    public void addUserToLoginAttempCached(String username) {
        int attempts = 0;
        try {
            attempts = ATTEMPTS_INCREMENT + loginAttemptCached.get(username);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        loginAttemptCached.put(username, attempts);
    }

    /**
     * método que valida si el usuario supero ellimite de intentos
     *
     * @param username
     * @return
     * @throws ExecutionException
     */
    public boolean hasExceededMaxAttempt(String username) {
        try {
            return loginAttemptCached.get(username) >= MAX_NUMBER_OF_ATTEMPTS;
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return false;
    }
}
