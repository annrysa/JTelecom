package com.jtelecom.services.impl;

import com.jtelecom.entities.addsOn.UserServiceCalls;
import com.jtelecom.entities.addsOn.UserServiceInternet;
import com.jtelecom.entities.addsOn.UserServiceRoaming;
import com.jtelecom.entities.addsOn.UserServices;
import com.jtelecom.entities.history.OrderHistory;
import com.jtelecom.entities.services.ServiceCalls;
import com.jtelecom.entities.services.ServiceInternet;
import com.jtelecom.entities.services.ServiceRoaming;
import com.jtelecom.exeption.UserFriendlyExeption;
import com.jtelecom.repositories.addsOn.UserServiceCallsRepository;
import com.jtelecom.repositories.addsOn.UserServiceInternetRepository;
import com.jtelecom.repositories.addsOn.UserServiceRoamingRepository;
import com.jtelecom.repositories.history.OrderHistoryRepository;
import com.jtelecom.services.AddsOnAllService;
import com.jtelecom.services.UserAddsOnService;
import com.jtelecom.ui.AddsOnType;
import com.jtelecom.ui.OrderAction;
import com.jtelecom.ui.OrderType;
import com.jtelecom.utils.DateConstructorUtil;
import com.jtelecom.utils.OrderRecordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserAddsOnServiceImpl implements UserAddsOnService {

    private UserServiceCallsRepository userServiceCallsRepository;
    private UserServiceInternetRepository userServiceInternetRepository;
    private UserServiceRoamingRepository userServiceRoamingRepository;
    private AddsOnAllService addsOnAllService;
    private OrderHistoryRepository orderHistoryRepository;

    @Autowired
    public void setUserServiceCallsRepository(UserServiceCallsRepository userServiceCallsRepository) {
        this.userServiceCallsRepository = userServiceCallsRepository;
    }

    @Autowired
    public void setUserServiceInternetRepository(UserServiceInternetRepository userServiceInternetRepository) {
        this.userServiceInternetRepository = userServiceInternetRepository;
    }

    @Autowired
    public void setUserServiceRoamingRepository(UserServiceRoamingRepository userServiceRoamingRepository) {
        this.userServiceRoamingRepository = userServiceRoamingRepository;
    }

    @Autowired
    public void setAddsOnAllService(AddsOnAllService addsOnAllService) {
        this.addsOnAllService = addsOnAllService;
    }

    @Autowired
    public void setOrderHistoryRepository(OrderHistoryRepository orderHistoryRepository) {
        this.orderHistoryRepository = orderHistoryRepository;
    }

    @Override
    public UserServiceInternet saveServiceInternet(Integer serviceId, Integer userId) throws UserFriendlyExeption {
        UserServiceInternet userServiceInternet = new UserServiceInternet(serviceId, userId);
        UserServiceInternet saveInternet = userServiceInternetRepository.save(userServiceInternet);
        if (saveInternet != null) {
            addAddsOnRecordByType(AddsOnType.INTERNET, serviceId, OrderAction.ACTIVATED, userId);
        }
        return saveInternet;
    }

    @Override
    public UserServiceRoaming saveServiceRoaming(Integer serviceId, Integer userId) throws UserFriendlyExeption {
        UserServiceRoaming userServiceRoaming = new UserServiceRoaming(serviceId, userId);
        UserServiceRoaming saveRoaming = userServiceRoamingRepository.save(userServiceRoaming);
        if (saveRoaming != null) {
            addAddsOnRecordByType(AddsOnType.ROAMING, serviceId, OrderAction.ACTIVATED, userId);
        }
        return saveRoaming;
    }

    @Override
    public UserServiceCalls saveServiceCalls(Integer serviceId, Integer userId) throws UserFriendlyExeption {
        UserServiceCalls userServiceCalls = new UserServiceCalls(serviceId, userId);
        UserServiceCalls saveCalls = userServiceCallsRepository.save(userServiceCalls);
        if (saveCalls != null) {
            addAddsOnRecordByType(AddsOnType.CALLS, serviceId, OrderAction.ACTIVATED, userId);
        }
        return saveCalls;
    }

    @Override
    public List<UserServices> findServicesByUserId(Integer userId) {
        List<UserServices> result = new ArrayList<>();
        List<UserServiceCalls> calls = userServiceCallsRepository.findAllByUserId(userId);
        List<UserServiceInternet> internets = userServiceInternetRepository.findAllByUserId(userId);
        List<UserServiceRoaming> roamings = userServiceRoamingRepository.findAllByUserId(userId);

        result.addAll(calls);
        result.addAll(internets);
        result.addAll(roamings);

        return result;
    }

    @Override
    public void deleteServiceInternet(Integer serviceId, Integer userId) throws UserFriendlyExeption {
        UserServiceInternet userServiceInternet = userServiceInternetRepository
                .findByServiceIdAndUserId(serviceId, userId);
        if (userServiceInternet != null) {
            userServiceInternetRepository.delete(userServiceInternet);
            addAddsOnRecordByType(AddsOnType.INTERNET, serviceId, OrderAction.DEACTIVATED, userId);
        }
    }

    @Override
    public void deleteServiceRoaming(Integer serviceId, Integer userId) throws UserFriendlyExeption {
        UserServiceRoaming userServiceRoaming = userServiceRoamingRepository
                .findByServiceIdAndUserId(serviceId, userId);
        if (userServiceRoaming != null) {
            userServiceRoamingRepository.delete(userServiceRoaming);
            addAddsOnRecordByType(AddsOnType.ROAMING, serviceId, OrderAction.DEACTIVATED, userId);
        }
    }

    @Override
    public void deleteServiceCalls(Integer serviceId, Integer userId) throws UserFriendlyExeption {
        UserServiceCalls userServiceCalls = userServiceCallsRepository
                .findUserServiceCallsByServiceIdAndUserId(serviceId, userId);
        if (userServiceCalls != null) {
            userServiceCallsRepository.delete(userServiceCalls);
            addAddsOnRecordByType(AddsOnType.CALLS, serviceId, OrderAction.DEACTIVATED, userId);
        }
    }

    private void addAddsOnRecordByType(AddsOnType addsOnType, Integer serviceId, OrderAction orderAction, Integer userId) throws UserFriendlyExeption {
        switch (addsOnType) {
            case CALLS:
                ServiceCalls serviceCallsByServiceId = addsOnAllService.findServiceCallsByServiceId(serviceId, userId);
                addAddsOnRecord(orderAction, serviceCallsByServiceId.getName(), userId);
                return;
            case INTERNET:
                ServiceInternet serviceInternetByServiceId = addsOnAllService.findServiceInternetByServiceId(serviceId, userId);
                addAddsOnRecord(orderAction, serviceInternetByServiceId.getName(), userId);
                return;
            case ROAMING:
                ServiceRoaming serviceRoamingByServiceId = addsOnAllService.findServiceRoamingByServiceId(serviceId, userId);
                addAddsOnRecord(orderAction, serviceRoamingByServiceId.getName(), userId);
                return;
            default:
                throw new UserFriendlyExeption("Service type not existing");
        }
    }

    private void addAddsOnRecord(OrderAction orderAction, String name, Integer userId) {
        String details = OrderRecordUtil.setOrderAction(orderAction, OrderType.SERVICE, name);
        OrderHistory orderHistory = new OrderHistory(userId, details, DateConstructorUtil.getOrderDate());
        orderHistoryRepository.save(orderHistory);
    }
}
