package com.security.springoauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;

@Configuration
public class SpringSecOAuthGitHubConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests().anyRequest().authenticated().and().oauth2Login();
  }

  /*private ClientRegistration clientRegistration() {
    return CommonOAuth2Provider.GITHUB.getBuilder("github").clientId("0937a1a09ad77ad7d59b")
      .clientSecret("dfacb56ed993e75c97442f84968938128a97e5af").build();
  }

  private ClientRegistration clientRegistration2() {
    ClientRegistration clientRegistration = ClientRegistration.withRegistrationId("github")
      .clientId("client_id")
      .clientSecret("client_secret")
      .scope("read:user")
      .authorizationUri("https://github.com/login/oauth/authorize")
      .tokenUri("https://github.com/login/oauth/access_token")
      .userInfoUri("https://api.github.com/user")
      .userNameAttributeName("id").clientName("GitHub")
      .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
      .redirectUriTemplate("{baseUrl}/{action}/oauth2/code/{registrationId}")
      .build();

    return clientRegistration;
  }

  @Bean
  public ClientRegistrationRepository clientRegistrationRepository() {
    ClientRegistration clientRegistration = clientRegistration();
    return new InMemoryClientRegistrationRepository(clientRegistration);
  }*/

}
