package com.app.auth.dao.User;

import com.app.auth.domain.login.LoginRequest;
import com.app.auth.domain.registration.Registration;
import com.app.auth.domain.registration.RegistrationRequest;
import com.app.auth.entity.User;

public interface UserDAO {

    User getUser(LoginRequest loginRequest);

    User getUser(RegistrationRequest registrationRequest);

    void updateUser(User user);

}
