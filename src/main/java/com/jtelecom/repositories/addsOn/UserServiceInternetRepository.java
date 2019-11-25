package com.jtelecom.repositories.addsOn;

import com.jtelecom.entities.addsOn.UserServiceInternet;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserServiceInternetRepository extends CrudRepository<UserServiceInternet, Integer> {
    UserServiceInternet findByServiceId(Integer serviceId);

    UserServiceInternet findByServiceIdAndUserId(Integer serviceId, Integer userId);

    List<UserServiceInternet> findAllByUserId(Integer userId);

    UserServiceInternet findByServiceIdAndUserIdAndIsActive(Integer serviceId, Integer userId, Integer isActive);

    UserServiceInternet findByUserId(Integer userId);
}
