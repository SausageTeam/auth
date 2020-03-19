package com.app.auth.dao.User.impl;

import com.app.auth.dao.AbstractHibernateDAO;
import com.app.auth.dao.User.UserDAO;
import com.app.auth.domain.login.LoginRequest;
import com.app.auth.domain.registration.Registration;
import com.app.auth.domain.registration.RegistrationRequest;
import com.app.auth.entity.RegistrationToken;
import com.app.auth.entity.User;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository
public class UserDAOImpl extends AbstractHibernateDAO<User> implements UserDAO {

    private static final String GET_USER = "FROM User WHERE username = :username AND password = :password";

    @Override
    public User getUser(LoginRequest loginRequest) {
        return getUser(loginRequest.getUsername(), loginRequest.getPassword());
    }

    @Override
    public User getUser(RegistrationRequest registrationRequest) {
        return getUser(registrationRequest.getUsername(), registrationRequest.getPassword());
    }

    private User getUser(String username, String password) {
        Session session = getCurrentSession();
        Query query = session.createQuery(GET_USER);
        query.setParameter("username", username);
        query.setParameter("password", password);

        @SuppressWarnings("unchecked")
        List<User> users = (List<User>) query.getResultList();
        if (users.size() > 0){
            return users.get(0);
        }
        else {
            return null;
        }
    }

    @Override
    public void updateUser(User user) {
        Session session = getCurrentSession();
        session.merge(user);
    }

}
