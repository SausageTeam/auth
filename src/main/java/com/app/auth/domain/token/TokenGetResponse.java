package com.app.auth.domain.token;

import com.app.auth.domain.common.GenericResponse;
import com.app.auth.domain.common.ServiceStatus;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenGetResponse extends GenericResponse {

    ServiceStatus serviceStatus;

}
