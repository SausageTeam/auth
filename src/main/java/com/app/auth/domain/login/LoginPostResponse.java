package com.app.auth.domain.login;

import com.app.auth.domain.common.GenericResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginPostResponse extends GenericResponse {

    private String redirectUrl;

}
