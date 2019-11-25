package com.jtelecom.services;

import com.jtelecom.entities.addsOn.UserServiceCalls;
import com.jtelecom.entities.addsOn.UserServiceInternet;
import com.jtelecom.entities.addsOn.UserServiceRoaming;
import com.jtelecom.entities.addsOn.UserServices;
import com.jtelecom.exeption.UserFriendlyExeption;

import java.util.List;

public interface UserAddsOnService {

    UserServiceInternet saveServiceInternet(Integer serviceId, Integer userId) throws UserFriendlyExeption;

    UserServiceRoaming saveServiceRoaming(Integer serviceId, Integer userId) throws UserFriendlyExeption;

    UserServiceCalls saveServiceCalls(Integer serviceId, Integer userId) throws UserFriendlyExeption;

    List<UserServices> findServicesByUserId(Integer userId);

    void deleteServiceInternet(Integer serviceId, Integer userId) throws UserFriendlyExeption;

    void deleteServiceRoaming(Integer serviceId, Integer userId) throws UserFriendlyExeption;

    void deleteServiceCalls(Integer serviceId, Integer userId) throws UserFriendlyExeption;
}
