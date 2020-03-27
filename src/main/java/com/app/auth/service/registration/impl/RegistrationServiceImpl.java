package com.app.auth.service.registration.impl;

import com.app.auth.dao.ApplicationWorkFlow.ApplicationWorkFlowDAO;
import com.app.auth.dao.Employee.EmployeeDAO;
import com.app.auth.dao.Person.PersonDAO;
import com.app.auth.dao.RegistrationToken.RegistrationTokenDAO;
import com.app.auth.dao.Role.RoleDAO;
import com.app.auth.dao.Role.enums.RoleEnums;
import com.app.auth.dao.User.UserDAO;
import com.app.auth.dao.UserRole.UserRoleDAO;
import com.app.auth.domain.registration.Registration;
import com.app.auth.entity.*;
import com.app.auth.security.util.AES;
import com.app.auth.service.registration.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.app.auth.constant.Constant.SECRET_KEY;
import static com.app.auth.dao.ApplicationWorkFlow.enums.ApplicationWorkFlowStatusEnums.ONBOARDING;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private UserDAO userDAO;

    private PersonDAO personDAO;

    private EmployeeDAO employeeDAO;

    private ApplicationWorkFlowDAO applicationWorkFlowDAO;

    private RoleDAO roleDAO;

    private UserRoleDAO userRoleDAO;

    private RegistrationTokenDAO registrationTokenDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Autowired
    public void setPersonDAO(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Autowired
    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Autowired
    public void setApplicationWorkFlowDAO(ApplicationWorkFlowDAO applicationWorkFlowDAO) {
        this.applicationWorkFlowDAO = applicationWorkFlowDAO;
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
    public void setRegistrationTokenDAO(RegistrationTokenDAO registrationTokenDAO) {
        this.registrationTokenDAO = registrationTokenDAO;
    }

    @Override
    @Transactional
    public User registerUser(String aesToken, Registration registration) {
        User user = null;

        String username = registration.getUsername();
        String password = registration.getPassword();

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formatDateTime = now.format(format);

        String decryptToken = AES.decrypt(aesToken, SECRET_KEY);
        if (decryptToken != null) {
            String[] arr_decryptToken = decryptToken.split(" ");
            String email = arr_decryptToken[0];
            String title = arr_decryptToken[1];
            String startDate = arr_decryptToken[2];
            String endDate = arr_decryptToken[3];

            Person person = Person.builder()
                    .email(email).build();
            person = personDAO.setPerson(person);

            user = User.builder()
                    .username(username)
                    .password(password)
                    .person(person)
                    .createDate(formatDateTime)
                    .modificationDate(formatDateTime)
                    .build();
            user = userDAO.setUser(user);

            Employee employee = Employee.builder()
                    .title(title)
                    .startDate(startDate)
                    .endDate(endDate)
                    .build();
            employee = employeeDAO.setEmployee(employee);

            ApplicationWorkFlow applicationWorkFlow = ApplicationWorkFlow.builder()
                    .employee(employee)
                    .createdDate(formatDateTime)
                    .modificationDate(formatDateTime)
                    .status(ONBOARDING.getValue())
                    .type("Onboarding")
                    .build();
            applicationWorkFlowDAO.setApplicationWorkFlow(applicationWorkFlow);

            Role role = roleDAO.getRoleById(RoleEnums.EMPLOYEE.getValue());
            UserRole userRole = UserRole.builder()
                    .user(user)
                    .role(role)
                    .createDate(formatDateTime)
                    .modificationDate(formatDateTime)
                    .build();
            userRoleDAO.setUserRole(userRole);

            RegistrationToken registrationToken = registrationTokenDAO.getRegistrationToken(aesToken);
            registrationToken.setActiveFlag(0);
            registrationTokenDAO.setRegistrationToken(registrationToken);
        }
        return user;
    }
}
