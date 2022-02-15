package com.spring.simple.security.service;

import com.spring.simple.security.models.entity.Usuario;

public interface IUsuarioService {

	Usuario findByUsername(String username);
}
