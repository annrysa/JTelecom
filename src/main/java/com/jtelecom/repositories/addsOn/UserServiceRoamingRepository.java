package com.jtelecom.repositories.addsOn;

import com.jtelecom.entities.addsOn.UserServiceRoaming;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserServiceRoamingRepository extends CrudRepository<UserServiceRoaming, Integer> {
    UserServiceRoaming findByServiceId(Integer serviceId);

    UserServiceRoaming findByServiceIdAndUserId(Integer serviceId, Integer userId);

    List<UserServiceRoaming> findAllByUserId(Integer userId);

    UserServiceRoaming findByUserId(Integer userId);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE UserServiceRoaming u SET u.serviceId =:serviceId WHERE u.id =:id")
    void updateServiceIdById(@Param("serviceId") Integer serviceId, @Param("id") Integer userId);
}
