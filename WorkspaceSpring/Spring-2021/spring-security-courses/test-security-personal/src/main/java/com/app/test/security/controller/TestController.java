package com.app.test.security.controller;

import com.app.test.security.dto.UserLoginDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController("/")
public class TestController
{
    @GetMapping("/all")
    public String testGetSample()
    {
        return "This url is public!";
    }

    @GetMapping("/public")
    public String testGet()
    {
        return "This url is public!";
    }

    @GetMapping("/private")
    public String testGetPrivate()
    {
        return "This url is private!";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDto userLoginDto)
    {
        return ResponseEntity.status(200).body(userLoginDto);
    }

}
