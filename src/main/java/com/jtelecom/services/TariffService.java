package com.jtelecom.services;

import com.jtelecom.entities.tariff.Tariff;
import com.jtelecom.entities.tariff.UserTariff;

public interface TariffService {
    Tariff findTariffById(Integer id);

    UserTariff findTariffByUserId(Integer userId);

    Iterable<Tariff> findAll();

    UserTariff save(Integer tariffId, Integer userId);

    void delete(Integer tariffId, Integer userId);

}
