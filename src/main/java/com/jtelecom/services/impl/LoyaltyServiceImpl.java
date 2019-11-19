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
import com.jtelecom.utils.OrderRecordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Loyalty findLoyaltyById(Integer loyaltyId) {
        return loyaltyRepository.findByLoyaltyId(loyaltyId);
    }

    @Override
    public Iterable<Loyalty> findAll() {
        return loyaltyRepository.findAll();
    }

    @Override
    public UserLoyalty save(Integer loyaltyId, Integer userId, String loyaltyCode, String dueDateActive, Integer isActive) {
        UserLoyalty userLoyalty = new UserLoyalty(loyaltyId, userId, loyaltyCode, dueDateActive, isActive);
        delete(userId);
        UserLoyalty result = userLoyaltyRepository.save(userLoyalty);
        if (result != null) {
            addLoyaltyRecord(OrderAction.ACTIVATED, userId, result.getLoyaltyId());
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
        UserLoyalty byUserId = userLoyaltyRepository.findByUserId(userId);
        if (byUserId != null) {
            userLoyaltyRepository.delete(byUserId);
            addLoyaltyRecord(OrderAction.DEACTIVATED, userId, byUserId.getLoyaltyId());
        }
    }

    private void addLoyaltyRecord(OrderAction orderAction, Integer userId, Integer loyaltyId) {
        Loyalty loyaltyById = findLoyaltyById(loyaltyId);
        String details = OrderRecordUtil.setOrderAction(orderAction, OrderType.LOYALTY, loyaltyById.getName());
        OrderHistory orderHistory = new OrderHistory(userId, details);
        orderHistoryRepository.save(orderHistory);
    }
}
