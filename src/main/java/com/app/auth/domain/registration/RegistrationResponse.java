package com.app.auth.domain.registration;

import com.app.auth.domain.common.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationResponse {

    private Status status;

    private String redirectUrl;

}
