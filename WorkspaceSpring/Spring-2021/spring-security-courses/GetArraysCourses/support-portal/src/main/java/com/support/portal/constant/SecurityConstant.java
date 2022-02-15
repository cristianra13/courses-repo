package com.support.portal.constant;

public class SecurityConstant {
    public static final long EXPIRATION_TIME = 3_600_000;// 1 HOUR xpressed in milliseconds
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String JWT_TOKEN_HEADER = "Jwt-Token";
    public static final String TOKEN_CANNOT_BE_VERIFIED = "Token cannot be verified";
    public static final String GET_COMPANY_COM = "My company, AS®";
    public static final String GET_COMPANY_ADMINISTRATION = "User Management Postal";
    public static final String AUTHORITIES = "authorities";
    public static final String FORBIDDEN_MESAGGE = "Your need to login to access this page";
    public static final String ACCESS_DENIED_MESSAGE = "You do not have permission to access this page";
    public static final String OPTIONS_HTTP_METHOD = "OPTIONS";
    public static final String[] PUBLIC_URLS = {"/user/login", "/user/register", "/user/image/**"};
    //public static final String[] PUBLIC_URLS = {"/user/login", "/user/register", "/user/reset-password/**", "/user/image/**"};
    // public static final String[] PUBLIC_URLS = {"**"}; // any request
}
