package com.jtelecom.services;

import com.jtelecom.entities.user.User;

public interface UserService {
    User findUserByEmail(String email);

    User saveUser(User user);
}
