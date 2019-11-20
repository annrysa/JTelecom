package com.jtelecom.controllers;

import com.jtelecom.entities.loyalty.Loyalty;
import com.jtelecom.entities.loyalty.UserLoyalty;
import com.jtelecom.services.LoyaltyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class LoyaltyController {

    private LoyaltyService loyaltyService;

    @Autowired
    public void setLoyaltyService(LoyaltyService loyaltyService) {
        this.loyaltyService = loyaltyService;
    }

    @RequestMapping(value = {"/loyalty"}, method = RequestMethod.GET)
    public ModelAndView loyaltyAll(ModelAndView modelAndView) {
        Iterable<Loyalty> loyalties = loyaltyService.findAll();
        modelAndView.addObject("loyalties", loyalties);
        modelAndView.setViewName("loyalty");
        return modelAndView;
    }

    @RequestMapping(value = {"/loyalty/{loyaltyId}"}, method = RequestMethod.GET)
    public ModelAndView loyaltyDetails(@PathVariable Integer loyaltyId, ModelAndView modelAndView) {
        Loyalty loyaltyInfo = loyaltyService.findLoyaltyById(loyaltyId);
        modelAndView.addObject("loyaltyInfo", loyaltyInfo);
        modelAndView.setViewName("loyalty-details");
        return modelAndView;
    }

    @RequestMapping(value = "/save-loyalty", method = RequestMethod.POST)
    public ModelAndView saveLoyalty(UserLoyalty userLoyalty) {
        ModelAndView modelAndView = new ModelAndView();
        UserLoyalty userLoyaltyInfo = loyaltyService.save(userLoyalty);
        modelAndView.addObject("successMessage", "Home Internet has been activated");
        modelAndView.addObject("userLoyaltyInfo", userLoyaltyInfo);
        modelAndView.setViewName("loyalty-details");

        return modelAndView;
    }
}
