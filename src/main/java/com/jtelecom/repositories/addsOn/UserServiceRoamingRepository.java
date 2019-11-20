package com.jtelecom.repositories.addsOn;

import com.jtelecom.entities.addsOn.UserServiceRoaming;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserServiceRoamingRepository extends CrudRepository<UserServiceRoaming, Integer> {
    UserServiceRoaming findByServiceId(Integer serviceId);

    UserServiceRoaming findByServiceIdAndUserId(Integer serviceId, Integer userId);

    List<UserServiceRoaming> findAllByUserId(Integer userId);

    UserServiceRoaming findByUserId(Integer userId);
}
