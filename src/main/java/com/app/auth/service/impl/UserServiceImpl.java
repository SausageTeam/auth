package com.app.auth.service.impl;

import com.app.auth.dao.UserDAO;
import com.app.auth.domain.auth.LoginDTO;
import com.app.auth.service.UserService;
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
    public boolean loginUser(LoginDTO loginDTO) {
        return userDAO.loginUser(loginDTO);
    }

}
