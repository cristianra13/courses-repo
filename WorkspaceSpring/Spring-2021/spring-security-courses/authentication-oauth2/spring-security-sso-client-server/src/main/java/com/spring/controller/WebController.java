package com.spring.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class WebController {

    @RequestMapping("/securedPage")
    public ResponseEntity<?> securedPage(@RequestBody Principal principal) {
        return ResponseEntity.ok("all ok");
    }

    @RequestMapping("/")
    public ResponseEntity<?> index(@RequestBody Principal principal) {
        return ResponseEntity.ok("all ok");
    }
}