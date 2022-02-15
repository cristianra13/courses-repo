package com.app.test.security.controller;

import com.app.test.security.dto.UserLoginDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/users")
public class AuthController
{
    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody UserLoginDto userLoginDto)
    {
        return null;
    }
}
