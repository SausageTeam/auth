package com.app.auth.domain.registration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationRequest {

    private String username;

    private String password;

    private String token;

    private String redirectUrl;

}
