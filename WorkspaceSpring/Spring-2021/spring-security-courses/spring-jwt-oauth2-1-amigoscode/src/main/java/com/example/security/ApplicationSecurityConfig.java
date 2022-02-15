package com.example.security;

import com.example.auth.ApplicationUserService;
import com.example.jwt.JwtConfig;
import com.example.jwt.JwtTokenVerifier;
import com.example.jwt.JwtUsernamePasswordAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.SecretKey;

import static com.example.security.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // preautoriza los recursos del controller
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter
{
    private final PasswordEncoder passwordEncoder;
    private final ApplicationUserService applicationUserService;
    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder, ApplicationUserService applicationUserService, JwtConfig jwtConfig, SecretKey secretKey)
    {
        this.passwordEncoder = passwordEncoder;
        this.applicationUserService = applicationUserService;
        this.jwtConfig = jwtConfig;
        this.secretKey = secretKey;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http
                //.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                //.and()
                .csrf().disable()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtUsernamePasswordAuthFilter(authenticationManager(), jwtConfig, secretKey))
                .addFilterAfter(new JwtTokenVerifier(secretKey, jwtConfig), JwtUsernamePasswordAuthFilter.class) // verificación despues del token
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
                .antMatchers("/api/**").hasRole(STUDENT.name())
                /*.antMatchers(DELETE,"/management/api/**").hasAnyAuthority(COURSE_WRITE.getPermission())
                .antMatchers(POST,"/management/api/**").hasAnyAuthority(COURSE_WRITE.getPermission())
                .antMatchers(PUT,"/management/api/**").hasAnyAuthority(COURSE_WRITE.getPermission())
                .antMatchers("/management/api/**").hasAnyRole(ADMIN.name(), ADMINTRAINEE.name())*/ // está linea es importante ya que si se agrega mas arriba, se le está permitiendo al ROL, que puede también eliminar o actualizar
                .anyRequest()
                .authenticated()
                /*.and()
                //.httpBasic()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .defaultSuccessUrl("/courses", true)
                    .passwordParameter("password")
                    .usernameParameter("username")
                .and()
                .rememberMe() // por defecto, 2 semanas
                    .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21))
                    .key("somethingverysecured")
                    .rememberMeParameter("remember-me")
                .and()
                .logout()
                    .logoutUrl("/logout")
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                    .clearAuthentication(true)
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID", "remember-me")
                    .logoutSuccessUrl("/login")*/
                ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider()
    {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(applicationUserService);

        return provider;
    }

    /**
     * Pasamos a cada usuario los permisis necesarios
     * @return
     */
    /*@Override
    @Bean
    protected UserDetailsService userDetailsService()
    {
        UserDetails cristian = User.builder()
                .username("cristian")
                .password(passwordEncoder.encode("123"))
                //.roles(STUDENT.name())
                .authorities(STUDENT.getGrantedAuthorities()) // pasamos los permisos del usuario
                .build();

        UserDetails maria = User.builder()
                .username("maria")
                .password(passwordEncoder.encode("123"))
                //.roles(ADMIN.name())
                .authorities(ADMIN.getGrantedAuthorities())
                .build();

        UserDetails anna = User.builder()
                .username("anna")
                .password(passwordEncoder.encode("123"))
                //.roles(STUDENT.name())
                .authorities(STUDENT.getGrantedAuthorities())
                .build();

        UserDetails tom = User.builder()
                .username("tom")
                .password(passwordEncoder.encode("123"))
                //.roles(ADMINTRAINEE.name())
                .authorities(ADMINTRAINEE.getGrantedAuthorities())
                .build();

        return new InMemoryUserDetailsManager(cristian, maria, anna, tom);
    }*/
}
