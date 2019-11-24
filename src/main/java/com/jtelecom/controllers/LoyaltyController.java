package com.jtelecom.controllers;

import com.jtelecom.entities.loyalty.Loyalty;
import com.jtelecom.entities.loyalty.UserLoyalty;
import com.jtelecom.services.LoyaltyService;
import com.jtelecom.services.UserService;
import com.jtelecom.utils.LoyaltyUtil;
import com.jtelecom.utils.ManagerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.util.HashSet;

@Controller
@RequestMapping("/user")
public class LoyaltyController {

    private LoyaltyService loyaltyService;
    private ManagerUtil managerUtil;
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setManagerUtil(ManagerUtil managerUtil) {
        this.managerUtil = managerUtil;
    }

    @Autowired
    public void setLoyaltyService(LoyaltyService loyaltyService) {
        this.loyaltyService = loyaltyService;
    }

    @RequestMapping(value = {"/loyalty"}, method = RequestMethod.GET)
    public ModelAndView loyaltyAll(ModelAndView modelAndView) throws ParseException {
        loyaltyService.updateLoyaltyStatus();
        Iterable<Loyalty> loyalties = loyaltyService.findAll(managerUtil.getAuthorizedUserId());
        modelAndView.addObject("loyalties", loyalties);
        modelAndView.setViewName("user/loyalty");
        return modelAndView;
    }

    @RequestMapping(value = {"/loyalty/{loyaltyId}"}, method = RequestMethod.GET)
    public ModelAndView loyaltyDetails(@PathVariable Integer loyaltyId, ModelAndView modelAndView) throws ParseException {
        loyaltyService.updateLoyaltyStatus();
        Loyalty loyaltyInfo = loyaltyService.findLoyaltyById(loyaltyId, managerUtil.getAuthorizedUserId());
        modelAndView.addObject("loyaltyInfo", loyaltyInfo);
        modelAndView.setViewName("user/loyalty-details");
        return modelAndView;
    }

    @RequestMapping(value = "/activateLoyalty/{loyaltyId}")
    public ModelAndView saveLoyalty(@PathVariable Integer loyaltyId) {
        ModelAndView modelAndView = new ModelAndView();
        Loyalty loyalty = loyaltyService.findLoyaltyById(loyaltyId, managerUtil.getAuthorizedUserId());
        Integer authorizedUserLoyalty = managerUtil.getAuthorizedUserLoyalty();
        if (loyalty.getAmount() > authorizedUserLoyalty) {
            modelAndView.addObject("loyaltyInfo", loyalty);
            System.out.println("Returning loyalty : " + loyalty);
            modelAndView.addObject("message", "You do not have enough bonuses!");
            System.out.println("message: You do not have enough bonuses!");
            modelAndView.setViewName("user/loyalty-details");
            return modelAndView;
        }
        UserLoyalty ulInfo = loyaltyService.save(LoyaltyUtil.generateUserLoyalty(loyaltyId, managerUtil.getAuthorizedUserId()));
        Loyalty userLoyaltyInfo = loyaltyService.findLoyaltyById(ulInfo.getLoyaltyId(), managerUtil.getAuthorizedUserId());
        HashSet<UserLoyalty> userLoyalties = new HashSet<>();
        userLoyalties.add(ulInfo);
        userLoyaltyInfo.setUserLoyalty(userLoyalties);
        userService.updateLoyalty(managerUtil.setLoyalty(userLoyaltyInfo.getAmount()), managerUtil.getAuthorizedUserId());
        modelAndView.addObject("successMessage", "Bonus " + userLoyaltyInfo.getName() + " been activated");
        modelAndView.addObject("loyaltyInfo", userLoyaltyInfo);
        modelAndView.setViewName("user/loyalty-details");

        return modelAndView;
    }
}
