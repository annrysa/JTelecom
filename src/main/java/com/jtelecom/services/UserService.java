package com.jtelecom.services;

import com.jtelecom.entities.user.User;

public interface UserService {
    User findUserByEmail(String email);

    User findUserById(Integer userId);

    User saveUser(User user);

    void updateLoyalty(Integer loyalty, Integer userId);

    void updateBalance(Integer balance, Integer userId);
}
