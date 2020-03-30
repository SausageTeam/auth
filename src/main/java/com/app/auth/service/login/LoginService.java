package com.app.auth.service.login;

import com.app.auth.domain.login.Login;
import com.app.auth.entity.User;

public interface LoginService {

    User loginUser(Login login);

}
