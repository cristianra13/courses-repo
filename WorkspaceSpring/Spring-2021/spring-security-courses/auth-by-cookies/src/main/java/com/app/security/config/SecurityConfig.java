package com.app.security.config;


import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    private final UserAuthenticationEntryPoint userAuthenticationEntryPoint;

    public SecurityConfig(UserAuthenticationEntryPoint userAuthenticationEntryPoint) {
        this.userAuthenticationEntryPoint = userAuthenticationEntryPoint;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http
                // captura las excepciones y lanza un json con el error
                .exceptionHandling().authenticationEntryPoint(userAuthenticationEntryPoint)
                .and()
                // añadimos la autenticación regular, como la basada en cookies
                .addFilterBefore(new UsernamePasswordAuthFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(new CookieAuthenticationFilter(), UsernamePasswordAuthFilter.class)
                .csrf().disable()
                // sinj sesión ya que es aplicación sin estado
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                // se elimina cookie al cerrar sesion
                .and()
                .logout().deleteCookies(CookieAuthenticationFilter.COOKIE_NAME)
                .and()
                .authorizeRequests()
                // estos dos no requieren autenticación, cualquier otro, si
                .antMatchers(HttpMethod.POST, "/v1/signIn", "/v1/signOut").permitAll()
                .anyRequest().authenticated();




    }
}
