package com.app.auth.domain.registration;

import com.app.auth.domain.common.GenericResponse;
import com.app.auth.domain.common.ServiceStatus;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationGetResponse extends GenericResponse {

    ServiceStatus serviceStatus;

}
