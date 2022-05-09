package com.example.springtutorialjwt;

public class AuthenticationConfigConstants {
    public static final String SECRET = "this is a temp secret";
    public static final long EXPIRATION_TIME = 864_000_000;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/api/user";
}
