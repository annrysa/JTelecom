package com.jtelecom.repositories.tariff;

import com.jtelecom.entities.tariff.UserTariff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserTariffRepository extends JpaRepository<UserTariff, Long> {

    @Query("FROM UserTariff ut where ut.userId =:userId")
    UserTariff findUserTariffByUserId(@Param("userId") Integer userId);

    UserTariff findUserTariffByUserIdAndTariffId(Integer userId, Integer tariffId);

    UserTariff deleteUserTariffByUserIdAndTariffId(Integer userId, Integer tariffId);

    UserTariff deleteUserTariffByUserId(Integer userId);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE UserTariff u SET u.tariffId =:tariffId WHERE u.userId =:userId")
    void updateTariffIdByUserId(@Param("tariffId") Integer tariffId, @Param("userId") Integer userId);
}
