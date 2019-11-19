package com.jtelecom.services.impl;

import com.jtelecom.entities.history.OrderHistory;
import com.jtelecom.entities.tariff.Tariff;
import com.jtelecom.entities.tariff.UserTariff;
import com.jtelecom.ui.OrderAction;
import com.jtelecom.ui.OrderType;
import com.jtelecom.repositories.history.OrderHistoryRepository;
import com.jtelecom.repositories.tariff.TariffRepository;
import com.jtelecom.repositories.tariff.UserTariffRepository;
import com.jtelecom.services.TariffService;
import com.jtelecom.utils.OrderRecordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class TariffServiceImpl implements TariffService {

    private TariffRepository tariffRepository;
    private UserTariffRepository userTariffRepository;
    private OrderHistoryRepository orderHistoryRepository;

    @Autowired
    public void setTariffRepository(TariffRepository tariffRepository) {
        this.tariffRepository = tariffRepository;
    }

    @Autowired
    public void setUserTariffRepository(UserTariffRepository userTariffRepository) {
        this.userTariffRepository = userTariffRepository;
    }

    @Autowired
    public void setOrderHistoryRepository(OrderHistoryRepository orderHistoryRepository) {
        this.orderHistoryRepository = orderHistoryRepository;
    }

    @Override
    public Tariff findTariffById(Integer id) {
        Optional<Tariff> tariffById = tariffRepository.findById(new Long(id));
        return tariffById.orElse(null);
    }

    @Override
    public Iterable<Tariff> findAll() {
        Iterable<Tariff> tariffs = tariffRepository.findAll();
        return tariffs;
    }

    @Override
    @Transactional
    public UserTariff save(Integer tariffId, Integer userId) {
        UserTariff userTariff = new UserTariff(tariffId, userId);
        UserTariff existingUserTariff = userTariffRepository.findUserTariffByUserId(userId);
        if (existingUserTariff != null) {
            delete(existingUserTariff.getTariffId(), existingUserTariff.getUserId());
        }

        UserTariff save = userTariffRepository.save(userTariff);
        if (save != null) {
            addTariffRecord(OrderAction.ACTIVATED, userId, tariffId);
        }

        return save;
    }

    @Override
    public void delete(Integer tariffId, Integer userId) {
        UserTariff userTariff = new UserTariff(tariffId, userId);
        userTariffRepository.delete(userTariff);
        UserTariff byUserIdAndTariffId = userTariffRepository.findUserTariffByUserIdAndTariffId(tariffId, userId);
        if (byUserIdAndTariffId == null) {
            addTariffRecord(OrderAction.DEACTIVATED, userId, tariffId);
        }
    }

    private void addTariffRecord(OrderAction orderAction, Integer userId, Integer tariffId) {
        Tariff tariffById = findTariffById(tariffId);
        if (tariffById != null) {
            String details = OrderRecordUtil.setOrderAction(orderAction, OrderType.TARIFF, tariffById.getName());
            OrderHistory orderHistory = new OrderHistory(userId, details);
            orderHistoryRepository.save(orderHistory);
        }
    }
}
