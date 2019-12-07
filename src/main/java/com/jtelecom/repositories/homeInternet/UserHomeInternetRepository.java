package com.jtelecom.repositories.homeInternet;

import com.jtelecom.entities.homeInternet.UserHomeInternet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserHomeInternetRepository extends JpaRepository<UserHomeInternet, Long> {

    UserHomeInternet findByHomeInternetIdAndUserId(Integer homeInternetId, Integer userId);

    UserHomeInternet findByHomeInternetIdAndUserIdAndIsActive(Integer homeInternetId, Integer userId, Integer isActive);

    UserHomeInternet findByUserId(Integer userId);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE UserHomeInternet u SET u.isActive =1 WHERE u.userId =:userId AND u.homeInternetId =:homeInternetId")
    void completeAppointment(@Param("userId") Integer userId, @Param("homeInternetId") Integer homeInternetId);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE UserHomeInternet u SET u.homeInternetId =:homeInternetId WHERE u.id =:id")
    void updateHomeInternetdByUserId(@Param("homeInternetId") Integer homeInternetId, @Param("id") Integer id);

}
