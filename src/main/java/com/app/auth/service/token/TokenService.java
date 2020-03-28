package com.app.auth.service.token;

import com.app.auth.domain.token.Token;

public interface TokenService {

    boolean isValidToken(Token token);

}
