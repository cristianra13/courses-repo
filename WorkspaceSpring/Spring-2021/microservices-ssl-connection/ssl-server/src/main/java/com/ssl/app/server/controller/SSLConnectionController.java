package com.ssl.app.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SSLConnectionController
{
	@GetMapping("/get-data")
	public String getData()
	{
		System.out.println("Llega al controller server");
		return "Hello world ssl connection";
	}
	
}
