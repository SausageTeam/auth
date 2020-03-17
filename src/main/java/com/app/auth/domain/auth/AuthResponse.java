package com.app.auth.domain.auth;

import com.app.auth.domain.common.Status;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthResponse {

    private Status status;

    private String redirectUrl;
}
