package com.app.auth.dao.impl;

import com.app.auth.dao.AbstractHibernateDAO;
import com.app.auth.dao.UserDAO;
import com.app.auth.domain.auth.LoginDTO;
import com.app.auth.entity.UserInfo;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class UserDAOImpl extends AbstractHibernateDAO<UserInfo> implements UserDAO {

    public UserDAOImpl() { setClazz(UserInfo.class); }

    @Override
    public boolean loginUser(LoginDTO loginDTO) {

        String userName = loginDTO.getUsername();
        String password = loginDTO.getPassword();

        CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<UserInfo> cq = cb.createQuery(UserInfo.class);
        Root<UserInfo> root = cq.from(UserInfo.class);


        cq.where(
                cb.and(
                        cb.equal(root.get("userName"), userName),
                        cb.equal(root.get("userPassword"), password)
                )
        );

        List<UserInfo> users = getCurrentSession().createQuery(cq).getResultList();

        return users.size() > 0;
    }

}
