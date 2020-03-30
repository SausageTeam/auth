package com.app.auth.dao.User;

import com.app.auth.entity.User;

public interface UserDAO {

    User getUser(String username, String password);

    User setUser(User user);

}
