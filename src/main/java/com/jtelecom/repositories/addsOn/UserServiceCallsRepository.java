package com.jtelecom.repositories.addsOn;

import com.jtelecom.entities.addsOn.UserServiceCalls;
import org.springframework.data.repository.CrudRepository;

public interface UserServiceCallsRepository extends CrudRepository<UserServiceCalls, Integer> {
    UserServiceCalls findUserServiceCallsByServiceId(Integer serviceId);

    UserServiceCalls findUserServiceCallsByServiceIdAndUserId(Integer serviceId, Integer userId);

    UserServiceCalls findUserServiceCallsByUserId(Integer userId);
}
