package com.jtelecom.utils;

import com.jtelecom.entities.user.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class ManagerUtil {

    public static Integer getAuthorizedUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User principal = (User) auth.getPrincipal();
        return principal != null ? principal.getId() : null;
    }
}
