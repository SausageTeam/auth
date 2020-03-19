package com.app.auth.service.registration;

import com.app.auth.domain.registration.Registration;
import com.app.auth.domain.registration.RegistrationRequest;

public interface RegistrationService {

    Registration registerUser(RegistrationRequest registrationRequest);

}
