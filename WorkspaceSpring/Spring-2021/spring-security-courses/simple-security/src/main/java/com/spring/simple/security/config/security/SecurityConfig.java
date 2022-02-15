package com.spring.simple.security.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService detailService;
	
	
	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// indicamos el tipo de autenticación a usar, en este caso, UserDetailsService
		// dentro de datail service se encuentra todo los datos del usuario como username y password
		auth.userDetailsService(detailService).passwordEncoder(encoder());
		
	}

	
	/**
	 * Configuramos las url's que tienen acceso publico y con autenticación
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests() // cualquier request que este autenticado
			.antMatchers("/", "/auth/**", "/public/**", "/css/**", "/js/**").permitAll().anyRequest().authenticated()
		.and() // pagina para cargar el login o encaso de error, cargar de igual manera el login
			.formLogin().loginPage("/auth/login").defaultSuccessUrl("/private/index", true).failureForwardUrl("/auth/login")
			.loginProcessingUrl("/auth/login-post").permitAll()
		.and()
			.logout().logoutUrl("/logout").logoutSuccessUrl("public/index");
	}

	
}
