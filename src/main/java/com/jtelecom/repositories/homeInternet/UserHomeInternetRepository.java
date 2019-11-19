package com.jtelecom.repositories.homeInternet;

import com.jtelecom.entities.homeInternet.UserHomeInternet;
import org.springframework.data.repository.CrudRepository;

public interface UserHomeInternetRepository extends CrudRepository<UserHomeInternet, Long> {

    UserHomeInternet findByHomeInternetIdAndUserId(Integer homeInternetId, Integer userId);

    UserHomeInternet findByUserId(Integer userId);
}
