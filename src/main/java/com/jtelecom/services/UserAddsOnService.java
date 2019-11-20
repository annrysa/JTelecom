package com.jtelecom.services;

import com.jtelecom.entities.addsOn.UserServices;
import com.jtelecom.exeption.UserFriendlyExeption;
import com.jtelecom.ui.AddsOnUiModel;

import java.util.List;

public interface UserAddsOnService {

    UserServices saveServiceByType(AddsOnUiModel addsOnUiModel, Integer userId) throws UserFriendlyExeption;

    List<UserServices> findServicesByUserId(Integer userId);

    void deleteServiceByType(AddsOnUiModel addsOnUiModel, Integer userId) throws UserFriendlyExeption;
}
