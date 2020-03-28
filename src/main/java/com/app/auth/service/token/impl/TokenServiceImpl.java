package com.app.auth.service.token.impl;

import com.app.auth.dao.RegistrationToken.RegistrationTokenDAO;
import com.app.auth.domain.token.Token;
import com.app.auth.domain.token.TokenPostRequest;
import com.app.auth.entity.RegistrationToken;
import com.app.auth.service.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.app.auth.constant.Constant.DEFAULT_DATE_TIME_FORMAT;

@Service
public class TokenServiceImpl implements TokenService {

    private RegistrationTokenDAO registrationTokenDAO;

    @Autowired
    public void setRegistrationTokenDAO(RegistrationTokenDAO registrationTokenDAO) {
        this.registrationTokenDAO = registrationTokenDAO;
    }

    @Override
    @Transactional
    public boolean isValidToken(Token token) {
        String aesToken = token.getAesToken();
        String email = token.getEmail();
        RegistrationToken registrationToken = registrationTokenDAO.getRegistrationToken(aesToken);
        if (registrationToken != null) {
            String createDateTime = registrationToken.getCreatedDateTime();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT);
            LocalDateTime expiryDate = LocalDateTime.parse(createDateTime, formatter).plusHours(registrationToken.getValidDuration());
            boolean usable = expiryDate.isAfter(LocalDateTime.now());
            boolean paired = registrationToken.getEmail().equals(email);
            boolean active = registrationToken.getActiveFlag() == 1;
            return usable && paired && active;
        } else {
            return false;
        }
    }

}
