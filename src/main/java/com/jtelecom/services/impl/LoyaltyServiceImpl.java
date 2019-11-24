package com.jtelecom.services.impl;

import com.jtelecom.entities.history.OrderHistory;
import com.jtelecom.entities.loyalty.Loyalty;
import com.jtelecom.entities.loyalty.UserLoyalty;
import com.jtelecom.repositories.history.OrderHistoryRepository;
import com.jtelecom.repositories.loyalty.LoyaltyRepository;
import com.jtelecom.repositories.loyalty.UserLoyaltyRepository;
import com.jtelecom.services.LoyaltyService;
import com.jtelecom.ui.OrderAction;
import com.jtelecom.ui.OrderType;
import com.jtelecom.utils.DateConstructorUtil;
import com.jtelecom.utils.OrderRecordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class LoyaltyServiceImpl implements LoyaltyService {

    private LoyaltyRepository loyaltyRepository;
    private UserLoyaltyRepository userLoyaltyRepository;
    private OrderHistoryRepository orderHistoryRepository;

    @Autowired
    public void setLoyaltyRepository(LoyaltyRepository loyaltyRepository) {
        this.loyaltyRepository = loyaltyRepository;
    }

    @Autowired
    public void setUserLoyaltyRepository(UserLoyaltyRepository userLoyaltyRepository) {
        this.userLoyaltyRepository = userLoyaltyRepository;
    }

    @Autowired
    public void setOrderHistoryRepository(OrderHistoryRepository orderHistoryRepository) {
        this.orderHistoryRepository = orderHistoryRepository;
    }

    @Override
    public Iterable<UserLoyalty> findLoyaltyByUserId(Integer userId) {
        return userLoyaltyRepository.findByUserId(userId);
    }

    @Override
    public Iterable<Loyalty> findAllLoyaltyByIds(List<Integer> loyaltyIds) {
        return loyaltyRepository.findAllByLoyaltyIdIn(loyaltyIds);
    }

    @Override
    public Iterable<UserLoyalty> findAll() {
        return userLoyaltyRepository.findAll();
    }

    @Override
    @Transactional
    public void updateLoyaltyStatus() throws ParseException {
        Iterable<UserLoyalty> all = userLoyaltyRepository.findAll();
        List<UserLoyalty> userLoyalties = new ArrayList<>();
        all.forEach(userLoyalties::add);

        for (UserLoyalty ul : userLoyalties) {
            if (DateConstructorUtil.compareDates(ul.getDueDateActive())) {
                userLoyaltyRepository.updateUserLoyaltyIsActiveById(0, ul.getId());
            }
        }
    }

    @Override
    public Loyalty findLoyaltyById(Integer loyaltyId, Integer userId) {
        Loyalty loyalty = loyaltyRepository.findByLoyaltyId(loyaltyId);
        List<UserLoyalty> ul = new ArrayList<>();
        Set<UserLoyalty> result = new HashSet<>();
        if (loyalty.getUserLoyalty() != null) {
            for (UserLoyalty userLoyalty : loyalty.getUserLoyalty()) {
                if (userLoyalty.getLoyaltyId() != null && userLoyalty.getUserId().equals(userId)) {
                    ul.add(userLoyalty);
                }
            }
            boolean isActive = false;
            if (!ul.isEmpty()) {
                UserLoyalty ulResult = ul.get(0);
                for (UserLoyalty userLoyalty : ul) {
                    if (!isActive && userLoyalty.getIsActive() == 1) {
                        isActive = true;
                        ulResult.setIsActive(1);
                    }
                }
                result.add(ulResult);
                loyalty.setUserLoyalty(result);
            }
        }
        return loyalty;
    }

    @Override
    public Iterable<Loyalty> findAll(Integer userId) {
        Iterable<Loyalty> loyalties = loyaltyRepository.findAll();
        List<UserLoyalty> ul = new ArrayList<>();
        Set<UserLoyalty> result = new HashSet<>();
        for (Loyalty loyalty : loyalties) {
            if (loyalty.getUserLoyalty() != null) {
                for (UserLoyalty userLoyalty : loyalty.getUserLoyalty()) {
                    if (userLoyalty.getLoyaltyId() != null && userLoyalty.getUserId().equals(userId)) {
                        ul.add(userLoyalty);
                    }
                }
                boolean isActive = false;
                if (!ul.isEmpty()) {
                    UserLoyalty ulResult = ul.get(0);
                    for (UserLoyalty userLoyalty : ul) {
                        if (!isActive && userLoyalty.getIsActive() == 1) {
                            isActive = true;
                            ulResult.setIsActive(1);
                        }
                    }
                    result.add(ulResult);
                    for (UserLoyalty userLoyalty : loyalty.getUserLoyalty()) {
                        if (userLoyalty.getLoyaltyId() != null && userLoyalty.getUserId().equals(userId)) {
                            loyalty.setUserLoyalty(result);
                        }
                    }
                }
            }
        }
        return loyalties;
    }

    @Override
    public UserLoyalty save(Integer loyaltyId, Integer userId, String loyaltyCode, String dueDateActive, Integer isActive) {
        UserLoyalty userLoyalty = new UserLoyalty(loyaltyId, userId, loyaltyCode, dueDateActive, isActive);
        UserLoyalty result = save(userLoyalty);
        return result;
    }

    @Override
    public UserLoyalty save(UserLoyalty userLoyalty) {
        UserLoyalty result = userLoyaltyRepository.save(userLoyalty);
        if (result != null) {
            addLoyaltyRecord(OrderAction.ACTIVATED, userLoyalty.getUserId(), result.getLoyaltyId());
        }
        return result;
    }

    @Override
    public void delete(Integer loyaltyId, Integer userId, String loyaltyCode, String dueDateActive, Integer isActive) {
        UserLoyalty userLoyalty = new UserLoyalty(loyaltyId, userId, loyaltyCode, dueDateActive, isActive);
        userLoyaltyRepository.delete(userLoyalty);
        addLoyaltyRecord(OrderAction.DEACTIVATED, userId, loyaltyId);
    }

    @Override
    public void delete(Integer userId) {
        Iterable<UserLoyalty> byUserId = userLoyaltyRepository.findByUserId(userId);
        if (byUserId != null) {
            byUserId.forEach(userLoyalty -> {
                userLoyaltyRepository.delete(userLoyalty);
                addLoyaltyRecord(OrderAction.DEACTIVATED, userId, userLoyalty.getLoyaltyId());
            });
        }
    }

    private void addLoyaltyRecord(OrderAction orderAction, Integer userId, Integer loyaltyId) {
        Loyalty loyaltyById = findLoyaltyById(loyaltyId, userId);
        String details = OrderRecordUtil.setOrderAction(orderAction, OrderType.LOYALTY, loyaltyById.getName());
        OrderHistory orderHistory = new OrderHistory(userId, details, DateConstructorUtil.getOrderDate());
        orderHistoryRepository.save(orderHistory);
    }
}
