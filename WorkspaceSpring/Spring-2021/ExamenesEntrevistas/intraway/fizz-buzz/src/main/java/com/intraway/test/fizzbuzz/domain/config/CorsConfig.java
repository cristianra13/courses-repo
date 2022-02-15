package com.intraway.test.fizzbuzz.domain.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Arrays;

/**
 * COnfiguración de cabeceras, corsfilter y metodos http
 */
@Configuration
@EnableWebMvc
@ConfigurationProperties(prefix="application.yml")
public class CorsConfig {

    private static final Long MAX_AGE = 3600L;
    private static final int CORS_FILTER_ORDER = -102;
    private static final String X_REQUESTED_WITH = "X-Requested-With";
    private static final String ORIGIN_ACCEPT = "Origin, Accept";

    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:4200");
        config.setAllowedHeaders(Arrays.asList(
                HttpHeaders.ORIGIN,
                HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN,
                HttpHeaders.CONTENT_TYPE,
                HttpHeaders.ACCEPT,
                HttpHeaders.AUTHORIZATION,
                ORIGIN_ACCEPT,
                X_REQUESTED_WITH,
                HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD,
                HttpHeaders.ACCESS_CONTROL_REQUEST_HEADERS
                ));
        config.setExposedHeaders(Arrays.asList(
                HttpHeaders.ORIGIN,
                HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN,
                HttpHeaders.CONTENT_TYPE,
                HttpHeaders.ACCEPT,
                HttpHeaders.AUTHORIZATION,
                HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN,
                HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS
        ));
        config.setAllowedMethods(Arrays.asList(
                HttpMethod.GET.name(),
                HttpMethod.POST.name(),
                HttpMethod.PUT.name(),
                HttpMethod.DELETE.name()));
        config.setMaxAge(MAX_AGE);
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(CORS_FILTER_ORDER);

        return bean;
    }
}
