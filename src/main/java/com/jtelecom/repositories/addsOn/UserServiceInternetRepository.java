package com.jtelecom.repositories.addsOn;

import com.jtelecom.entities.addsOn.UserServiceInternet;
import org.springframework.data.repository.CrudRepository;

public interface UserServiceInternetRepository extends CrudRepository<UserServiceInternet, Integer> {
    UserServiceInternet findByServiceId(Integer serviceId);

    UserServiceInternet findByServiceIdAndUserId(Integer serviceId, Integer userId);

    UserServiceInternet findByUserId(Integer userId);
}
