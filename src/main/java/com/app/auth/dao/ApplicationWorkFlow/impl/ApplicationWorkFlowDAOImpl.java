package com.app.auth.dao.ApplicationWorkFlow.impl;

import com.app.auth.dao.AbstractHibernateDAO;
import com.app.auth.dao.ApplicationWorkFlow.ApplicationWorkFlowDAO;
import com.app.auth.entity.ApplicationWorkFlow;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class ApplicationWorkFlowDAOImpl extends AbstractHibernateDAO<ApplicationWorkFlow> implements ApplicationWorkFlowDAO {

    public ApplicationWorkFlowDAOImpl() { setClazz(ApplicationWorkFlow.class);}

    @Override
    public void setApplicationWorkFlow(ApplicationWorkFlow applicationWorkFlow) {
        Session session = getCurrentSession();
        session.merge(applicationWorkFlow);
    }

}
