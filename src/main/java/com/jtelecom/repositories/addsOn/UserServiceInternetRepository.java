package com.jtelecom.repositories.addsOn;

import com.jtelecom.entities.addsOn.UserServiceInternet;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserServiceInternetRepository extends CrudRepository<UserServiceInternet, Integer> {
    UserServiceInternet findByServiceId(Integer serviceId);

    UserServiceInternet findByServiceIdAndUserId(Integer serviceId, Integer userId);

    List<UserServiceInternet> findAllByUserId(Integer userId);

    UserServiceInternet findByUserId(Integer userId);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE UserServiceInternet u SET u.serviceId =:serviceId WHERE u.id =:id")
    void updateServiceIdById(@Param("serviceId") Integer serviceId, @Param("id") Integer userId);
}
