package com.app.auth.dao.Person.impl;

import com.app.auth.dao.AbstractHibernateDAO;
import com.app.auth.dao.Person.PersonDAO;
import com.app.auth.entity.Person;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class PersonDAOImpl extends AbstractHibernateDAO<Person> implements PersonDAO {

    @Override
    public Person updatePerson(Person person) {
        Session session = getCurrentSession();
        return (Person) session.merge(person);
    }

}
