package com.app.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAccountController {

  @GetMapping("/my-account")
  public String contact() {
    return "My Account";
  }
}
