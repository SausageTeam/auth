package com.app.auth.domain.login;

import com.app.auth.domain.common.GenericResponse;
import com.app.auth.domain.common.ServiceStatus;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginGetResponse extends GenericResponse {

    ServiceStatus serviceStatus;

}
