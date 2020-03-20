package com.app.auth.service.registration;

import com.app.auth.domain.registration.RegistrationRequest;
import com.app.auth.entity.User;

import javax.servlet.http.HttpServletRequest;

public interface RegistrationService {

    User registerUser(HttpServletRequest httpServletRequest, RegistrationRequest registrationRequest);

}
