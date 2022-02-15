package com.support.portal.configuration;

import com.support.portal.filter.JWTAccessDeniedHandler;
import com.support.portal.filter.JWTAuthenticationEntryPoint;
import com.support.portal.filter.JWTAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.support.portal.constant.SecurityConstant.PUBLIC_URLS;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // nos ayuda a preAutorizar los roles en el controller
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private JWTAuthorizationFilter jwtAuthorizationFilter;
    private JWTAccessDeniedHandler jwtAccessDeniedHandler;
    private JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private UserDetailsService userDetailsService;
    private BCryptPasswordEncoder passwordEncoder;
    private CorsConfig webConfig;

    @Autowired
    public SecurityConfig(JWTAuthorizationFilter jwtAuthorizationFilter,
                          JWTAccessDeniedHandler jwtAccessDeniedHandler,
                          JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint,
                          @Qualifier("userDetailsService") UserDetailsService userDetailsService,
                          BCryptPasswordEncoder passwordEncoder) {
        this.jwtAuthorizationFilter = jwtAuthorizationFilter;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Método para autenticar al usuario
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder)
        ;
    }

    /**
     * Método para dar seguridad a los endponts
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(PUBLIC_URLS).permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().accessDeniedHandler(jwtAccessDeniedHandler)// en caso de autenticación fallida, mandamos el handler de accees denied
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
        ;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
