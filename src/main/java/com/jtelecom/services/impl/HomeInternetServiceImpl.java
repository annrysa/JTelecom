package com.jtelecom.services.impl;

import com.jtelecom.entities.history.OrderHistory;
import com.jtelecom.entities.homeInternet.HomeInternet;
import com.jtelecom.entities.homeInternet.UserHomeInternet;
import com.jtelecom.repositories.history.OrderHistoryRepository;
import com.jtelecom.repositories.homeInternet.HomeInternetRepository;
import com.jtelecom.repositories.homeInternet.UserHomeInternetRepository;
import com.jtelecom.services.HomeInternetService;
import com.jtelecom.ui.OrderAction;
import com.jtelecom.ui.OrderType;
import com.jtelecom.utils.DateConstructorUtil;
import com.jtelecom.utils.OrderRecordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
public class HomeInternetServiceImpl implements HomeInternetService {

    private HomeInternetRepository homeInternetRepository;
    private UserHomeInternetRepository userHomeInternetRepository;
    private OrderHistoryRepository orderHistoryRepository;

    @Autowired
    public void setHomeInternetRepository(HomeInternetRepository homeInternetRepository) {
        this.homeInternetRepository = homeInternetRepository;
    }

    @Autowired
    public void setUserHomeInternetRepository(UserHomeInternetRepository userHomeInternetRepository) {
        this.userHomeInternetRepository = userHomeInternetRepository;
    }

    @Autowired
    public void setOrderHistoryRepository(OrderHistoryRepository orderHistoryRepository) {
        this.orderHistoryRepository = orderHistoryRepository;
    }

    @Override
    public HomeInternet findHomeInternetById(Integer id, Integer userId) {
        HomeInternet hm = homeInternetRepository.findByHomeInternetId(id);
        UserHomeInternet userHomeInternet = userHomeInternetRepository
                .findByHomeInternetIdAndUserIdAndIsActive(hm.getHomeInternetId(), userId, 1);
        Set<UserHomeInternet> uhm = new HashSet<>();
        uhm.add(userHomeInternet);
        hm.setUserHomeInternet(userHomeInternet == null ? null : uhm);
        return hm;
    }

    @Override
    public HomeInternet findHomeInternetDetailsById(Integer id, Integer userId) {
        HomeInternet hm = homeInternetRepository.findByHomeInternetId(id);
        UserHomeInternet userHomeInternet = userHomeInternetRepository
                .findByHomeInternetIdAndUserId(hm.getHomeInternetId(), userId);
        Set<UserHomeInternet> uhm = new HashSet<>();
        uhm.add(userHomeInternet);
        hm.setUserHomeInternet(userHomeInternet == null ? null : uhm);
        return hm;
    }

    @Override
    public UserHomeInternet findByInternetIdAndUserId(Integer id, Integer userId) {
        return userHomeInternetRepository.findByHomeInternetIdAndUserId(id, userId);
    }

    @Override
    public UserHomeInternet findUserHomeInternetByUserId(Integer userId) {
        return userHomeInternetRepository.findByUserId(userId);
    }

    @Override
    public Iterable<HomeInternet> findAll(Integer userId) {
        Iterable<HomeInternet> all = homeInternetRepository.findAll();
        for (HomeInternet hm : all) {
            UserHomeInternet userHomeInternet = userHomeInternetRepository
                    .findByHomeInternetIdAndUserId(hm.getHomeInternetId(), userId);
            Set<UserHomeInternet> uhm = new HashSet<>();
            uhm.add(userHomeInternet);
            hm.setUserHomeInternet(userHomeInternet == null ? null : uhm);
        }
        return all;
    }

    @Override
    public UserHomeInternet save(Integer homeInternetId, Integer userId, String appointment, Integer isActive) {
        UserHomeInternet userHomeInternet = new UserHomeInternet(homeInternetId, userId, appointment, isActive);
        UserHomeInternet result = save(userHomeInternet);
        return result;
    }

    @Override
    @Transactional
    public void completeAppointment(Integer homeInternetId, Integer userId) {
        userHomeInternetRepository.completeAppointment(userId, homeInternetId);
    }

    @Override
    public UserHomeInternet save(UserHomeInternet userHomeInternet) {
        delete(userHomeInternet.getUserId());
        UserHomeInternet result = userHomeInternetRepository.save(userHomeInternet);
        if (result != null) {
            addHomeInternetRecord(OrderAction.ACTIVATED, userHomeInternet.getUserId(), result.getHomeInternetId());
        }
        return result;
    }

    @Override
    @Transactional
    public void update(Integer serviceId, Integer id) {
        userHomeInternetRepository.updateHomeInternetdByUserId(serviceId, id);
    }

    @Override
    public void delete(Integer homeInternetId, Integer userId, String appointment, Integer isActive) {
        UserHomeInternet userHomeInternet = new UserHomeInternet(homeInternetId, userId, appointment, isActive);
        userHomeInternetRepository.delete(userHomeInternet);
        addHomeInternetRecord(OrderAction.DEACTIVATED, userId, homeInternetId);
    }

    @Override
    public void delete(Integer userId) {
        UserHomeInternet byUserId = findUserHomeInternetByUserId(userId);
        if (byUserId != null) {
            userHomeInternetRepository.delete(byUserId);
            addHomeInternetRecord(OrderAction.DEACTIVATED, userId, byUserId.getHomeInternetId());
        }
    }

    private void addHomeInternetRecord(OrderAction orderAction, Integer userId, Integer internetId) {
        HomeInternet homeInternetById = findHomeInternetById(internetId, userId);
        String details = OrderRecordUtil.setOrderAction(orderAction, OrderType.HOME_INTERNET, homeInternetById.getName());
        OrderHistory orderHistory = new OrderHistory(userId, details, DateConstructorUtil.getOrderDate());
        orderHistoryRepository.save(orderHistory);
    }
}
