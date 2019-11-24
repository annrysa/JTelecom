package com.jtelecom.services.impl;

import com.jtelecom.entities.history.OrderHistory;
import com.jtelecom.entities.tariff.Tariff;
import com.jtelecom.entities.tariff.UserTariff;
import com.jtelecom.repositories.history.OrderHistoryRepository;
import com.jtelecom.repositories.tariff.TariffRepository;
import com.jtelecom.repositories.tariff.UserTariffRepository;
import com.jtelecom.repositories.user.UserRepository;
import com.jtelecom.services.TariffService;
import com.jtelecom.ui.OrderAction;
import com.jtelecom.ui.OrderType;
import com.jtelecom.utils.DateConstructorUtil;
import com.jtelecom.utils.ManagerUtil;
import com.jtelecom.utils.OrderRecordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class TariffServiceImpl implements TariffService {

    private TariffRepository tariffRepository;
    private UserTariffRepository userTariffRepository;
    private OrderHistoryRepository orderHistoryRepository;
    private UserRepository userRepository;
    private ManagerUtil managerUtil;

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

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setManagerUtil(ManagerUtil managerUtil) {
        this.managerUtil = managerUtil;
    }

    @Override
    public Tariff findTariffById(Integer id, Integer userId) {
        Tariff tariffById = tariffRepository.findByTariffId(id);
        if (tariffById.getUserTariff() != null && (tariffById.getUserTariff().stream().filter(userTariff -> userTariff.getUserId() != userId).findAny().orElse(null)) != null) {
            tariffById.setUserTariff(null);
        }
        return tariffById;
    }

    @Override
    public UserTariff findTariffByUserId(Integer userId) {
        return userTariffRepository.findUserTariffByUserId(userId);
    }

    @Override
    public Iterable<Tariff> findAll(Integer userId) {
        Iterable<Tariff> tariffs = tariffRepository.findAll();
        for (Tariff tariff : tariffs) {
            if (tariff.getUserTariff() != null && (tariff.getUserTariff().stream().filter(userTariff -> userTariff.getUserId() != userId).findAny().orElse(null)) != null) {
                tariff.setUserTariff(null);
            }
        }
        return tariffs;
    }

    @Override
    @Transactional
    public UserTariff updateTariffIdByUserId(Integer tariffId, UserTariff oldTariff, Integer userId) {
        userTariffRepository.updateTariffIdByUserId(tariffId, userId);
        UserTariff update = userTariffRepository.findUserTariffByUserIdAndTariffId(userId, tariffId);
        if (update != null) {
            addTariffRecord(OrderAction.DEACTIVATED, userId, oldTariff.getTariffId());
            addTariffRecord(OrderAction.ACTIVATED, userId, tariffId);
            Tariff byTariffId = tariffRepository.findByTariffId(update.getTariffId());
            userRepository.updateBalance(update.getUserId(), managerUtil.setBalance(byTariffId.getPrice()));
        }

        return update;
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

    @Override
    @Transactional
    public void delete(UserTariff userTariff) {
        userTariffRepository.delete(userTariff);
        UserTariff byUserIdAndTariffId = userTariffRepository.findUserTariffByUserIdAndTariffId(userTariff.getUserId(), userTariff.getTariffId());
        if (byUserIdAndTariffId == null) {
            addTariffRecord(OrderAction.DEACTIVATED, userTariff.getUserId(), userTariff.getTariffId());
        }
    }

    @Transactional
    public void addTariffRecord(OrderAction orderAction, Integer userId, Integer tariffId) {
        Tariff tariffById = findTariffById(tariffId, userId);
        if (tariffById != null) {
            String details = OrderRecordUtil.setOrderAction(orderAction, OrderType.TARIFF, tariffById.getName());
            OrderHistory orderHistory = new OrderHistory(userId, details, DateConstructorUtil.getOrderDate());
            orderHistoryRepository.save(orderHistory);
        }
    }
}
