package com.app.auth.domain.registration;

import com.app.auth.domain.common.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationResponse {

    private String username;

    private String password;

    private String email;

    private Status status;

    private String redirectUrl;

}
