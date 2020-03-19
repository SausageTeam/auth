package com.app.auth.service.token;

import com.app.auth.domain.token.TokenRequest;

public interface TokenService {

    boolean isValidToken(TokenRequest tokenRequest);

}
