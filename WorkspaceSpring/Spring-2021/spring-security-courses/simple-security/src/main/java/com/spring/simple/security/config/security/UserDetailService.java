package com.spring.simple.security.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

import com.spring.simple.security.models.entity.Usuario;
import com.spring.simple.security.models.repository.UsuarioRepository;

public class UserDetailService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByUsername(username);
		UserBuilder userBuilder = null;
		
		if(usuario != null) {
			userBuilder = User.withUsername(username);
			userBuilder.disabled(false);
			userBuilder.password(usuario.getPassword());
			userBuilder.authorities(new SimpleGrantedAuthority("ROLE_USER"));
		} else {
			throw new UsernameNotFoundException("Usuario no encontrado");
		}
		
		// retornamos el usuario construido
		return userBuilder.build(); 
	}

	
}
