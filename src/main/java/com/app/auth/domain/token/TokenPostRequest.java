package com.app.auth.domain.token;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenPostRequest {

    private String email;

    private String aesToken;

    private String redirectUrl;

}
