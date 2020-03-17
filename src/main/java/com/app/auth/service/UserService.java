package com.app.auth.service;

import com.app.auth.domain.auth.LoginDTO;

public interface UserService {

    boolean loginUser(LoginDTO loginDTO);
}
