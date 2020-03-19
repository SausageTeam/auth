package com.app.auth.service.auth.impl;

import com.app.auth.dao.User.UserDAO;
import com.app.auth.domain.login.LoginRequest;
import com.app.auth.service.auth.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    @Transactional
    public boolean loginUser(LoginRequest loginRequest) {
        return userDAO.getUser(loginRequest) != null;
    }

}
