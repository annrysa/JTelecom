package com.jtelecom.services;

import com.jtelecom.entities.addsOn.UserServices;
import com.jtelecom.exeption.UserFriendlyExeption;
import com.jtelecom.ui.AddsOnUiModel;

public interface UserAddsOnService {

    UserServices saveServiceByType(AddsOnUiModel addsOnUiModel, Integer userId) throws UserFriendlyExeption;

    void deleteServiceByType(AddsOnUiModel addsOnUiModel, Integer userId) throws UserFriendlyExeption;
}
