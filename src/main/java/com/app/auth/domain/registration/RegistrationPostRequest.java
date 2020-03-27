package com.app.auth.domain.registration;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationPostRequest {

    private String username;

    private String password;

    private String redirectUrl;

}
