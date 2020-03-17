package com.app.auth.domain.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {

    private String username;

    private String password;

    private String redirectUrl;

}