package com.app.auth.service.registration;

import com.app.auth.domain.registration.Registration;
import com.app.auth.entity.User;

public interface RegistrationService {

    User registerUser(String token, Registration registration);

}
