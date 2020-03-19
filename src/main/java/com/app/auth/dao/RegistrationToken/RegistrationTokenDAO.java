package com.app.auth.dao.RegistrationToken;

import com.app.auth.domain.registration.RegistrationRequest;
import com.app.auth.domain.token.Token;
import com.app.auth.domain.token.TokenRequest;
import com.app.auth.entity.RegistrationToken;

public interface RegistrationTokenDAO {

    RegistrationToken getRegistrationToken(TokenRequest tokenRequest);

    RegistrationToken getRegistrationToken(RegistrationRequest registrationRequest);

}
