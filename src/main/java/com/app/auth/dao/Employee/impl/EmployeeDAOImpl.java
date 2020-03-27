package com.app.auth.dao.Employee.impl;

import com.app.auth.dao.AbstractHibernateDAO;
import com.app.auth.dao.Employee.EmployeeDAO;
import com.app.auth.entity.Employee;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDAOImpl extends AbstractHibernateDAO<Employee> implements EmployeeDAO {

    public EmployeeDAOImpl() { setClazz(Employee.class);}

    @Override
    public Employee setEmployee(Employee employee) {
        Session session = getCurrentSession();
        return (Employee) session.merge(employee);
    }

}
