package com.spring.springsecurityssoauthserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController
{
    @GetMapping("/user/me")
    public Principal user(Principal principal)
    {
        System.out.println(principal);
        return principal;
    }
}
