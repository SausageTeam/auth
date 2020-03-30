package com.app.auth.dao.RegistrationToken;

import com.app.auth.entity.RegistrationToken;

public interface RegistrationTokenDAO {

    RegistrationToken getRegistrationToken(String token);

    void setRegistrationToken(RegistrationToken registrationToken);

}
