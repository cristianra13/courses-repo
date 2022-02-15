package com.support.portal.configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

import static com.support.portal.constant.SecurityConstant.JWT_TOKEN_HEADER;

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

    @Value("${urls}")
    private final List<String> urls = new ArrayList<>();

    public List<String> getUrls() {
        return this.urls;
    }

    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        // getUrls().forEach(url -> config.addAllowedOrigin(url));
        // TODO validar el permiso a verias urls
        //config.addAllowedOrigin("http://localhost:4200");
        config.setAllowedOrigins(Arrays.asList("http://localhost:4200", "http://3.15.155.251", "http://ec2-3-15-155-251.us-east-2.compute.amazonaws.com", "https://3.15.155.251", "https://ec2-3-15-155-251.us-east-2.compute.amazonaws.com"));
        config.setAllowedHeaders(Arrays.asList(
                HttpHeaders.ORIGIN,
                HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN,
                HttpHeaders.CONTENT_TYPE,
                HttpHeaders.ACCEPT,
                HttpHeaders.AUTHORIZATION,
                ORIGIN_ACCEPT,
                X_REQUESTED_WITH,
                HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD,
                HttpHeaders.ACCESS_CONTROL_REQUEST_HEADERS,
                JWT_TOKEN_HEADER
                ));
        config.setExposedHeaders(Arrays.asList(
                HttpHeaders.ORIGIN,
                HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN,
                HttpHeaders.CONTENT_TYPE,
                HttpHeaders.ACCEPT,
                JWT_TOKEN_HEADER,
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

        // should be set order to -100 because we need to CorsFilter before SpringSecurityFilter
        bean.setOrder(CORS_FILTER_ORDER);
        return bean;
    }
}
