package com.app.auth.dao.User.impl;

import com.app.auth.dao.AbstractHibernateDAO;
import com.app.auth.dao.User.UserDAO;
import com.app.auth.entity.User;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDAOImpl extends AbstractHibernateDAO<User> implements UserDAO {

    private static final String GET_USERS = "FROM User WHERE (username = :username OR email = :username) AND password = :password";

    @Override
    public User getUser(String username, String password) {
        Session session = getCurrentSession();
        Query query = session.createQuery(GET_USERS);
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
    public User updateUser(User user) {
        Session session = getCurrentSession();
        return (User) session.merge(user);
    }

}
