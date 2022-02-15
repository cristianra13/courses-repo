package com.ssl.app.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssl.app.client.service.ISSLService;

@RestController
public class ClientController 
{
	@Autowired
	private ISSLService service;
	
	@GetMapping("/called")
	public void called()
	{
		service.consumeServerService();
	}

}
