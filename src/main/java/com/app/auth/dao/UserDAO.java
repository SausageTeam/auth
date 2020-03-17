package com.app.auth.dao;

import com.app.auth.domain.auth.LoginDTO;

public interface UserDAO {

    boolean loginUser(LoginDTO loginDTO);
}
