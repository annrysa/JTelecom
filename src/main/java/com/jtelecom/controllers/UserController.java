package com.jtelecom.controllers;

import com.jtelecom.entities.addsOn.UserServices;
import com.jtelecom.entities.homeInternet.UserHomeInternet;
import com.jtelecom.entities.loyalty.UserLoyalty;
import com.jtelecom.entities.tariff.UserTariff;
import com.jtelecom.entities.user.User;
import com.jtelecom.services.*;
import com.jtelecom.utils.ManagerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private TariffService tariffService;
    private UserAddsOnService userAddsOnService;
    private HomeInternetService homeInternetService;
    private LoyaltyService loyaltyService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setTariffService(TariffService tariffService) {
        this.tariffService = tariffService;
    }

    @Autowired
    public void setUserAddsOnService(UserAddsOnService userAddsOnService) {
        this.userAddsOnService = userAddsOnService;
    }

    @Autowired
    public void setHomeInternetService(HomeInternetService homeInternetService) {
        this.homeInternetService = homeInternetService;
    }

    @Autowired
    public void setLoyaltyService(LoyaltyService loyaltyService) {
        this.loyaltyService = loyaltyService;
    }

    @RequestMapping(value = {"/user-info"}, method = RequestMethod.GET)
    public ModelAndView userInfo(ModelAndView modelAndView) {
        Integer authorizedUserId = ManagerUtil.getAuthorizedUserId();
        User userInfo = userService.findUserById(authorizedUserId);
        UserTariff tariffInfo = tariffService.findTariffByUserId(authorizedUserId);
        List<UserServices> servicesInfo = userAddsOnService.findServicesByUserId(authorizedUserId);
        UserHomeInternet homeInternetInfo = homeInternetService.findUserHomeInternetByUserId(authorizedUserId);
        UserLoyalty loyaltyInfo = loyaltyService.findLoyaltyByUserId(authorizedUserId);
        modelAndView.addObject("userInfo", userInfo);
        modelAndView.addObject("tariffInfo", tariffInfo);
        modelAndView.addObject("servicesInfo", servicesInfo);
        modelAndView.addObject("homeInternetInfo", homeInternetInfo);
        modelAndView.addObject("loyaltyInfo", loyaltyInfo);
        modelAndView.setViewName("user-info");
        return modelAndView;
    }

    @RequestMapping(value = "/user-edit", method = RequestMethod.GET)
    public ModelAndView getUserEdit(ModelAndView modelAndView) {
        User userEdit = userService.findUserById(ManagerUtil.getAuthorizedUserId());
        modelAndView.addObject("userEdit", userEdit);
        modelAndView.setViewName("user-edit");

        return modelAndView;
    }

    @RequestMapping(value = "/user-edit", method = RequestMethod.POST)
    public ModelAndView updateUserId(@Valid User user, BindingResult bindingResult, ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("user-edit");
        } else {
            User userEdit = userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("userEdit", userEdit);
            modelAndView.setViewName("user-edit");

        }
        return modelAndView;
    }
}
