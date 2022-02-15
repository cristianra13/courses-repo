package com.spring.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/")
public class TestController {

	@GetMapping("/test")
	public ResponseEntity<?> testUrl() {
		return ResponseEntity.ok("exitoso");
	}
}
