package com.app.auth.domain.token;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenRequest {

    private String email;

    private String token;

    private String redirectUrl;

}
