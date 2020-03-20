package com.app.auth.service.login.impl;

import com.app.auth.dao.User.UserDAO;
import com.app.auth.domain.login.LoginRequest;
import com.app.auth.entity.User;
import com.app.auth.service.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginServiceImpl implements LoginService {

    private UserDAO userDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    @Transactional
    public User loginUser(LoginRequest loginRequest) {
        return userDAO.getUser(loginRequest.getUsername(), loginRequest.getPassword());
    }

}
