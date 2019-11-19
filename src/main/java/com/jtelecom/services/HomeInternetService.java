package com.jtelecom.services;

import com.jtelecom.entities.homeInternet.HomeInternet;
import com.jtelecom.entities.homeInternet.UserHomeInternet;

public interface HomeInternetService {
    HomeInternet findHomeInternetById(Integer id);

    UserHomeInternet findByInternetIdAndUserId(Integer id, Integer userId);

    UserHomeInternet findUserHomeInternetByUserId(Integer userId);

    Iterable<HomeInternet> findAll();

    UserHomeInternet save(Integer homeInternetId, Integer userId, String appointment, Integer isActive);

    void delete(Integer homeInternetId, Integer userId, String appointment, Integer isActive);

    void delete(Integer userId);

}
