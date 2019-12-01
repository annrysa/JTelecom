package com.jtelecom.services;

import com.jtelecom.entities.homeInternet.HomeInternet;
import com.jtelecom.entities.homeInternet.UserHomeInternet;

public interface HomeInternetService {
    HomeInternet findHomeInternetById(Integer id, Integer userId);

    HomeInternet findHomeInternetDetailsById(Integer id, Integer userId);

    UserHomeInternet findByInternetIdAndUserId(Integer id, Integer userId);

    UserHomeInternet findUserHomeInternetByUserId(Integer userId);

    Iterable<HomeInternet> findAll(Integer userId);

    UserHomeInternet save(Integer homeInternetId, Integer userId, String appointment, Integer isActive);

    void completeAppointment(Integer homeInternetId, Integer userId);

    UserHomeInternet save(UserHomeInternet userHomeInternet);

    void delete(Integer homeInternetId, Integer userId, String appointment, Integer isActive);

    void delete(Integer userId);

}
