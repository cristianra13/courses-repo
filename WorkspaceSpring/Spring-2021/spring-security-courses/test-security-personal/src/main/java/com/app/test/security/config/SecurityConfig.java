package com.app.test.security.config;

import com.app.test.security.config.jwt.JWTAuthenticationFilter;
import com.app.test.security.config.jwt.JWTAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    private PasswordEncoder passwordEncoder;
    private UserDetailServiceImpl userDetailService;

    @Autowired
    public SecurityConfig(PasswordEncoder passwordEncoder, UserDetailServiceImpl userDetailService)
    {
        this.passwordEncoder = passwordEncoder;
        this.userDetailService = userDetailService;
    }


    //@Autowired
    //public void configurerGlobal(AuthenticationManagerBuilder builder) throws Exception
    //{
    //    /**
    //     * Por cada password que pase, este se va a encriptar
    //     * retornando el password encriptado en BCrypt
    //     */
    //    User.UserBuilder users = User.builder().passwordEncoder(passwordEncoder::encode);
    //
    //    /**
    //     * En este punto, cargamos usuarios en memoria para hacer la respectiva validación de login
    //     */
    //    builder.inMemoryAuthentication()
    //            .withUser(users.username("admin").password("123").roles("ADMIN", "USER"))
    //            .withUser(users.username("cristian").password("123").roles("USER"));
    //}


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(userDetailService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, SecurityConstants.SIGN_UP_URL).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                // this disables session creation on Spring Security
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

                //.antMatchers("/all", "/public").permitAll()
                //.antMatchers("/private").hasAnyRole("ADMIN")
                //.anyRequest().authenticated()
                //.and()
                //.formLogin().permitAll()
                //.and()
                //.logout().permitAll()

    }

}
