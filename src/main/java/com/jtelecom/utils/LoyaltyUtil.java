package com.jtelecom.utils;

import com.jtelecom.entities.loyalty.Loyalty;
import com.jtelecom.entities.loyalty.UserLoyalty;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class LoyaltyUtil {


    public static UserLoyalty generateUserLoyalty(Integer loyaltyId, Integer userId) {
        UserLoyalty result = new UserLoyalty();
        result.setLoyaltyId(loyaltyId);
        result.setLoyaltyCode(generateLoyaltyCode());
        result.setDueDateActive(DateConstructorUtil.getPlusDayDate(10));
        result.setIsActive(1);
        result.setUserId(userId);

        return result;

    }

    private static String generateLoyaltyCode() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 15; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    private static Loyalty checkLoyaltyStatus(Loyalty loyalty) {
        Set<UserLoyalty> result = new HashSet<>();
        Set<UserLoyalty> userLoyalties = loyalty.getUserLoyalty();
        for (UserLoyalty userLoyalty : userLoyalties) {
            try {
                userLoyalty.setIsActive(DateConstructorUtil.compareDates(userLoyalty.getDueDateActive()) ? 0 : 1);
                result.add(userLoyalty);
            } catch (ParseException e) {
                return loyalty;
            }
        }
        loyalty.setUserLoyalty(result);
        return loyalty;
    }
}
