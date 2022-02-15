package com.support.app.controllers;

import com.support.app.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/user")
public class UserController
{
    @GetMapping("/home")
    public ResponseEntity<?> showUser()
    {
        return ResponseEntity.ok().body("Application Works");
    }
}
