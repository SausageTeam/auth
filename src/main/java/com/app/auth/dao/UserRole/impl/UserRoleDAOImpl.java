package com.app.auth.dao.UserRole.impl;

import com.app.auth.dao.AbstractHibernateDAO;
import com.app.auth.dao.UserRole.UserRoleDAO;
import com.app.auth.entity.UserRole;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class UserRoleDAOImpl extends AbstractHibernateDAO<UserRole> implements UserRoleDAO {

    @Override
    public UserRole setUserRole(UserRole userRole) {
        Session session = getCurrentSession();
        return (UserRole) session.merge(userRole);
    }

}
