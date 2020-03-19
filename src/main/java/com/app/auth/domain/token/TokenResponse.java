package com.app.auth.domain.token;

import com.app.auth.domain.common.Status;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TokenResponse {

    private String token;

    private Status status;

    private String redirectUrl;

}
