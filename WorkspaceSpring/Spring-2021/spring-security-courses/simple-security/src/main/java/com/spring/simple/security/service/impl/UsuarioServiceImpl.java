package com.spring.simple.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.simple.security.models.entity.Usuario;
import com.spring.simple.security.models.repository.UsuarioRepository;
import com.spring.simple.security.service.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public Usuario findByUsername(String username) {
		return usuarioRepository.findByUsername(username);
	}

	
}
