package com.app.auth.dao.RegistrationToken.impl;

import com.app.auth.dao.AbstractHibernateDAO;
import com.app.auth.dao.RegistrationToken.RegistrationTokenDAO;
import com.app.auth.entity.RegistrationToken;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class RegistrationTokenDAOImpl extends AbstractHibernateDAO<RegistrationToken> implements RegistrationTokenDAO {

    private static final String GET_REGISTRATION_TOKEN = "FROM RegistrationToken WHERE token = :token";

    @Override
    public RegistrationToken getRegistrationToken(String token) {
        Session session = getCurrentSession();
        Query query = session.createQuery(GET_REGISTRATION_TOKEN);
        query.setParameter("token", token);

        @SuppressWarnings("unchecked")
        List<RegistrationToken> registrationTokens = query.getResultList();
        return (registrationTokens.size() > 0) ? registrationTokens.get(0) : null;
    }

    @Override
    public void setRegistrationToken(RegistrationToken registrationToken) {
        Session session = getCurrentSession();
        session.merge(registrationToken);
    }

}
