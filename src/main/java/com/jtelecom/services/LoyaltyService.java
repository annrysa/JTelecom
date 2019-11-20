package com.jtelecom.services;

import com.jtelecom.entities.loyalty.Loyalty;
import com.jtelecom.entities.loyalty.UserLoyalty;

public interface LoyaltyService {
    Loyalty findLoyaltyById(Integer loyaltyId);

    UserLoyalty findLoyaltyByUserId(Integer userId);

    Iterable<Loyalty> findAll();

    UserLoyalty save(Integer loyaltyId, Integer userId, String loyaltyCode, String dueDateActive, Integer isActive);

    UserLoyalty save(UserLoyalty userLoyalty);

    void delete(Integer loyaltyId, Integer userId, String loyaltyCode, String dueDateActive, Integer isActive);

    void delete(Integer userId);

}
