package com.jtelecom.services;

import com.jtelecom.entities.tariff.Tariff;
import com.jtelecom.entities.tariff.UserTariff;

public interface TariffService {
    Tariff findTariffById(Integer id, Integer userId);

    UserTariff findTariffByUserId(Integer userId);

    Iterable<Tariff> findAll(Integer userId);

    UserTariff updateTariffIdByUserId(Integer tariffId, UserTariff oldTariff, Integer userId);

    void delete(Integer tariffId, Integer userId);

    void delete(UserTariff userTariff);

}
