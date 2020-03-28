package com.app.auth.constant;

public interface Constant {
    String DEFAULT_URL = "http://localhost:4200/";

    String JWT_TOKEN_COOKIE_NAME = "JWT-TOKEN";
    String SIGNING_KEY = "signingKey";

    int DEFAULT_REGISTRATION_TOKEN_VALID_DURATION = 3;
    int ACTIVE_FLAG = 1;
    int INACTIVE_FLAG = 0;

    String SECRET_KEY = "sausage";

    String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
}