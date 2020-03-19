package com.app.auth.dao.RegistrationToken.impl;

import com.app.auth.dao.AbstractHibernateDAO;
import com.app.auth.dao.RegistrationToken.RegistrationTokenDAO;
import com.app.auth.domain.registration.RegistrationRequest;
import com.app.auth.domain.registration.RegistrationResponse;
import com.app.auth.domain.token.Token;
import com.app.auth.domain.token.TokenRequest;
import com.app.auth.entity.RegistrationToken;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class RegistrationTokenDAOImpl extends AbstractHibernateDAO<RegistrationToken> implements RegistrationTokenDAO {

    private static final String GET_REGISTRATION_TOKEN = "FROM RegistrationToken WHERE token = :token";

    @Override
    public RegistrationToken getRegistrationToken(TokenRequest tokenRequest) {
        return getRegistrationToken(tokenRequest.getToken());
    }

    @Override
    public RegistrationToken getRegistrationToken(RegistrationRequest registrationRequest) {
        return getRegistrationToken(registrationRequest.getToken());
    }

    private RegistrationToken getRegistrationToken(String token) {
        Session session = getCurrentSession();
        Query query = session.createQuery(GET_REGISTRATION_TOKEN);
        query.setParameter("token", token);

        @SuppressWarnings("unchecked")
        List<RegistrationToken> registrationTokens = (List<RegistrationToken>) query.getResultList();
        if (registrationTokens.size() > 0){
            return registrationTokens.get(0);
        }
        else {
            return null;
        }
    }

}
