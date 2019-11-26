package com.jtelecom.utils;

import com.jtelecom.entities.user.User;
import com.jtelecom.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ManagerUtil {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    public User getAuthorizedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        return user;
    }

    public Integer getAuthorizedUserId() {
        User user = getAuthorizedUser();
        return user != null ? user.getId() : null;
    }

    public Integer getAuthorizedUserBalance() {
        User user = getAuthorizedUser();
        return user != null ? user.getBalance() : 0;
    }

    public Integer getAuthorizedUserLoyalty() {
        User user = getAuthorizedUser();
        return user != null ? user.getLoyalty() : 0;
    }

    public Integer setBalance(Integer price) {
        return getAuthorizedUser().getBalance() - price;
    }

    public Integer fillBalance(Integer sum) {
        return getAuthorizedUser().getBalance() + sum;
    }

    public Integer setLoyalty(Integer loyalty) {
        return getAuthorizedUser().getLoyalty() - loyalty;
    }

    public Integer fillLoyalty(Integer price) {
        return getAuthorizedUser().getLoyalty() + (price/10);
    }
}
