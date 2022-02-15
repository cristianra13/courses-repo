package com.sergio.socialnetwork.config;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
/**
 * Manejo de autenticaciòn a traves de filtros, esto nos permite que se ejecuten antes de llegar loas
 * peticiones a los controladores.
 *
 * Para autenticacion por token, este debe realizar un proceso de validaciòn primero.
 * este debe ser enviado en todas las llamadas resliadas por el frontend en un encabezado
 * en caso de que el token sea correcto par5a autenticaciòn, se permite el ingreso al controller.
 *
 * Para la autenticaciòn por usuario  y password, tendremos una unica forma de autenticar un usuario
 * a travès de los filtros HTTP
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserAuthenticationEntryPoint userAuthenticationEntryPoint;
    /*
        Componente que se encargara de validar el token y se comunicara
        con el servicio de autenticacion para validar usuario y password
     */
    private final UserAuthenticationProvider userAuthenticationProvider;

    public SecurityConfig(UserAuthenticationEntryPoint userAuthenticationEntryPoint,
                          UserAuthenticationProvider userAuthenticationProvider) {
        this.userAuthenticationEntryPoint = userAuthenticationEntryPoint;
        this.userAuthenticationProvider = userAuthenticationProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .exceptionHandling().authenticationEntryPoint(userAuthenticationEntryPoint)
                .and()
                // filtro dedicado a manejar la autenticación regular por usuario y contraseña
                .addFilterBefore(new UsernamePasswordAuthFilter(userAuthenticationProvider), BasicAuthenticationFilter.class)
                // filtro dedicado a manejar la autenticación por JWT token
                .addFilterBefore(new JwtAuthFilter(userAuthenticationProvider), UsernamePasswordAuthFilter.class)
                .csrf().disable()
                // indicamos que no serà creada la sesiòn ya que estamos en una app STATELESS
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // metodos que NO requeriran autenticaciòn
                .antMatchers(HttpMethod.POST, "/v1/singIn", "/v1/singUp").permitAll()
                // el resto requiere autenticaciòn
                .anyRequest().authenticated();
    }

}
