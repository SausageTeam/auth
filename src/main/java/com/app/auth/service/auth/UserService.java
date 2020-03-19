package com.app.auth.service.auth;

import com.app.auth.domain.login.LoginRequest;

public interface UserService {

    boolean loginUser(LoginRequest loginRequest);

}
