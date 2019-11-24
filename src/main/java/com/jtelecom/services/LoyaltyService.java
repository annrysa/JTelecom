package com.jtelecom.services;

import com.jtelecom.entities.loyalty.Loyalty;
import com.jtelecom.entities.loyalty.UserLoyalty;

import java.text.ParseException;
import java.util.List;

public interface LoyaltyService {
    Loyalty findLoyaltyById(Integer loyaltyId, Integer userId);

    Iterable<UserLoyalty> findLoyaltyByUserId(Integer userId);

    Iterable<UserLoyalty> findAll();

    void updateLoyaltyStatus() throws ParseException;

    Iterable<Loyalty> findAllLoyaltyByIds(List<Integer> loyaltyIds);

    Iterable<Loyalty> findAll(Integer userId);

    UserLoyalty save(Integer loyaltyId, Integer userId, String loyaltyCode, String dueDateActive, Integer isActive);

    UserLoyalty save(UserLoyalty userLoyalty);

    void delete(Integer loyaltyId, Integer userId, String loyaltyCode, String dueDateActive, Integer isActive);

    void delete(Integer userId);

}
