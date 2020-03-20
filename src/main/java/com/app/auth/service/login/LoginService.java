package com.app.auth.service.login;

import com.app.auth.domain.login.LoginRequest;
import com.app.auth.entity.User;

public interface LoginService {

    User loginUser(LoginRequest loginRequest);

}
