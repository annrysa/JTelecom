package com.jtelecom.repositories.tariff;

import com.jtelecom.entities.tariff.UserTariff;
import org.springframework.data.repository.CrudRepository;

public interface UserTariffRepository extends CrudRepository<UserTariff, Long> {

    UserTariff findUserTariffByUserId(Integer userId);

    UserTariff findUserTariffByUserIdAndTariffId(Integer userId, Integer tariffId);

    UserTariff deleteUserTariffByUserIdAndTariffId(Integer userId, Integer tariffId);

    UserTariff deleteUserTariffByUserId(Integer userId);
}
