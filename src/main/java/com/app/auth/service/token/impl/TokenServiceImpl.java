package com.app.auth.service.token.impl;

import com.app.auth.dao.RegistrationToken.RegistrationTokenDAO;
import com.app.auth.domain.token.TokenRequest;
import com.app.auth.entity.RegistrationToken;
import com.app.auth.service.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class TokenServiceImpl implements TokenService {

    private RegistrationTokenDAO registrationTokenDAO;

    @Autowired
    public void setRegistrationTokenDAO(RegistrationTokenDAO registrationTokenDAO) {
        this.registrationTokenDAO = registrationTokenDAO;
    }

    @Override
    @Transactional
    public boolean isValidToken(TokenRequest tokenRequest) {
        String token = tokenRequest.getToken();
        String email = tokenRequest.getEmail();

        RegistrationToken registrationToken = registrationTokenDAO.getRegistrationToken(token);
        if (registrationToken != null) {
            String createdBy = registrationToken.getCreatedBy();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime expiryDate = LocalDateTime.parse(createdBy, formatter).plusHours(registrationToken.getValidDuration());
            boolean usable = expiryDate.isAfter(LocalDateTime.now());
            boolean paired = registrationToken.getEmail().equals(email);
            boolean active = registrationToken.getActiveFlag() == 1;
            return usable && paired && active;
        } else {
            return false;
        }
    }

}
