package com.app.auth.service.registration.impl;

import com.app.auth.dao.Person.PersonDAO;
import com.app.auth.dao.RegistrationToken.RegistrationTokenDAO;
import com.app.auth.dao.Role.RoleDAO;
import com.app.auth.dao.Role.enums.RoleEnums;
import com.app.auth.dao.User.UserDAO;
import com.app.auth.dao.UserRole.UserRoleDAO;
import com.app.auth.domain.registration.RegistrationRequest;
import com.app.auth.entity.*;
import com.app.auth.service.registration.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private UserDAO userDAO;

    private RoleDAO roleDAO;

    private UserRoleDAO userRoleDAO;


    private PersonDAO personDAO;

    private RegistrationTokenDAO registrationTokenDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Autowired
    public void setRoleDAO(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Autowired
    public void setUserRoleDAO(UserRoleDAO userRoleDAO) {
        this.userRoleDAO = userRoleDAO;
    }

    @Autowired
    public void setPersonDAO(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Autowired
    public void setRoleDAO(RegistrationTokenDAO registrationTokenDAO) {
        this.registrationTokenDAO = registrationTokenDAO;
    }


    private Person updatePerson(String email){
        Person person = new Person();
        person.setEmail(email);
        return personDAO.updatePerson(person);
    }

    private User updateUser(String username, String password, String email, Person person, String createDate, String ModificationDate) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setPerson(person);
        user.setCreateDate(createDate);
        user.setModificationDate(ModificationDate);
        return userDAO.updateUser(user);
    }

    private void updateUserRole(User user, Role role, String createDate, String ModificationDate) {
        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);
        userRole.setActiveFlag(1);
        userRole.setCreateDate(createDate);
        userRole.setModificationDate(ModificationDate);
        userRole.setLastModificationUser(0);
        userRoleDAO.updateUserRole(userRole);
    }

    @Override
    @Transactional
    public User registerUser(HttpServletRequest httpServletRequest, RegistrationRequest registrationRequest) {
        String username = registrationRequest.getUsername();
        String password = registrationRequest.getPassword();
        HttpSession session = httpServletRequest.getSession();
        String token = (String) session.getAttribute("token");

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formatDateTime = now.format(format);

        RegistrationToken registrationToken = registrationTokenDAO.getRegistrationToken(token);
        String email = registrationToken.getEmail();
        registrationTokenDAO.updateInvalidRegistrationToken(registrationToken.getId());

        Person person = updatePerson(email);

        User user = updateUser(username, password, email, person, formatDateTime, formatDateTime);

        Role role = roleDAO.getRoleById(RoleEnums.EMPLOYEE.getValue());

        updateUserRole(user, role, formatDateTime, formatDateTime);

        return user;
    }
}
