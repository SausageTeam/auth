package com.app.auth.domain.login;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Login {

    private String username;

    private String password;

    private String redirectUrl;

}
