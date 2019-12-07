package com.jtelecom.repositories.addsOn;

import com.jtelecom.entities.addsOn.UserServiceCalls;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserServiceCallsRepository extends CrudRepository<UserServiceCalls, Integer> {
    UserServiceCalls findUserServiceCallsByServiceId(Integer serviceId);

    UserServiceCalls findUserServiceCallsByServiceIdAndUserId(Integer serviceId, Integer userId);

    List<UserServiceCalls> findAllByUserId(Integer userId);

    UserServiceCalls findUserServiceCallsByUserId(Integer userId);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE UserServiceCalls u SET u.serviceId =:serviceId WHERE u.id =:id")
    void updateServiceIdById(@Param("serviceId") Integer serviceId, @Param("id") Integer userId);
}
