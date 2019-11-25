package com.jtelecom.repositories.addsOn;

import com.jtelecom.entities.addsOn.UserServiceCalls;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserServiceCallsRepository extends CrudRepository<UserServiceCalls, Integer> {
    UserServiceCalls findUserServiceCallsByServiceId(Integer serviceId);

    UserServiceCalls findUserServiceCallsByServiceIdAndUserId(Integer serviceId, Integer userId);

    List<UserServiceCalls> findAllByUserId(Integer userId);

    UserServiceCalls findByServiceIdAndUserIdAndIsActive(Integer serviceId, Integer userId, Integer isActive);

    UserServiceCalls findUserServiceCallsByUserId(Integer userId);
}
