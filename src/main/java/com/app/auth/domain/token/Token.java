package com.app.auth.domain.token;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Token implements Serializable {

    private String email;

    private String aesToken;

    private String redirectUrl;

}
