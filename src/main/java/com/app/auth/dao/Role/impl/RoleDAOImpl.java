package com.app.auth.dao.Role.impl;

import com.app.auth.dao.AbstractHibernateDAO;
import com.app.auth.dao.Role.RoleDAO;
import com.app.auth.entity.Role;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDAOImpl extends AbstractHibernateDAO<Role> implements RoleDAO {

    public RoleDAOImpl() {
        setClazz(Role.class);
    }

    @Override
    public Role getRoleById(int id) {
        return findById(id);
    }
}
