package com.app.auth.service.registration.impl;

import com.app.auth.dao.RegistrationToken.RegistrationTokenDAO;
import com.app.auth.dao.User.UserDAO;
import com.app.auth.domain.registration.Registration;
import com.app.auth.domain.registration.RegistrationRequest;
import com.app.auth.entity.RegistrationToken;
import com.app.auth.entity.User;
import com.app.auth.service.registration.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private UserDAO userDAO;
    private RegistrationTokenDAO registrationTokenDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Autowired
    public void setRegistrationTokenDAO(RegistrationTokenDAO registrationTokenDAO) {
        this.registrationTokenDAO = registrationTokenDAO;
    }

    @Override
    @Transactional
    public Registration registerUser(RegistrationRequest registrationRequest) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formatDateTime = now.format(format);
        User user = new User();
        user.setUsername(registrationRequest.getUsername());
        user.setPassword(registrationRequest.getPassword());
        user.setCreateDate(formatDateTime);
        user.setModificationDate(formatDateTime);
        userDAO.updateUser(user);

        RegistrationToken registrationToken = registrationTokenDAO.getRegistrationToken(registrationRequest);
        Registration registration = new Registration();
        registration.setUsername(user.getUsername());
        registration.setPassword(user.getPassword());
        registration.setEmail(registrationToken.getEmail());
        return registration;
    }
}
