package com.jtelecom.controllers;

import com.jtelecom.converters.UiModelToOrderModelConverter;
import com.jtelecom.entities.addsOn.UserServices;
import com.jtelecom.entities.homeInternet.HomeInternet;
import com.jtelecom.entities.homeInternet.UserHomeInternet;
import com.jtelecom.entities.loyalty.Loyalty;
import com.jtelecom.entities.loyalty.UserLoyalty;
import com.jtelecom.entities.tariff.Tariff;
import com.jtelecom.entities.tariff.UserTariff;
import com.jtelecom.entities.user.User;
import com.jtelecom.services.*;
import com.jtelecom.ui.AppointmentUi;
import com.jtelecom.ui.LoyaltyInfoUi;
import com.jtelecom.ui.PayloadUi;
import com.jtelecom.utils.ManagerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private TariffService tariffService;
    private UserAddsOnService userAddsOnService;
    private HomeInternetService homeInternetService;
    private LoyaltyService loyaltyService;
    private ManagerUtil managerUtil;
    private UiModelToOrderModelConverter uiModelToOrderModelConverter;

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
    public void setManagerUtil(ManagerUtil managerUtil) {
        this.managerUtil = managerUtil;
    }

    @Autowired
    public void setLoyaltyService(LoyaltyService loyaltyService) {
        this.loyaltyService = loyaltyService;
    }

    @Autowired
    public void setUiModelToOrderModelConverter(UiModelToOrderModelConverter uiModelToOrderModelConverter) {
        this.uiModelToOrderModelConverter = uiModelToOrderModelConverter;
    }

    @RequestMapping(value = {"/user-info"}, method = RequestMethod.GET)
    public ModelAndView userInfo(ModelAndView modelAndView) throws ParseException {
        Integer authorizedUserId = managerUtil.getAuthorizedUserId();
        User userInfo = managerUtil.getAuthorizedUser();
        UserTariff userTariff = tariffService.findTariffByUserId(authorizedUserId);
        Tariff tariffInfo = tariffService.findTariffById(userTariff.getTariffId(), authorizedUserId);
        List<UserServices> servicesInfo = userAddsOnService.findServicesByUserId(authorizedUserId);
        UserHomeInternet homeInternetInfo = homeInternetService.findUserHomeInternetByUserId(authorizedUserId);
        HomeInternet homeInternetById = homeInternetService.findHomeInternetById(homeInternetInfo.getHomeInternetId(), managerUtil.getAuthorizedUserId());
        loyaltyService.updateLoyaltyStatus();
        Iterable<UserLoyalty> loyaltyInfo = loyaltyService.findLoyaltyByUserId(authorizedUserId);
        List<Integer> loyaltyIds = new ArrayList<>();
        for (UserLoyalty loyaltyById : loyaltyInfo) {
            loyaltyIds.add(loyaltyById.getLoyaltyId());
        }
        Iterable<Loyalty> loyaltiesById = loyaltyService.findAllLoyaltyByIds(loyaltyIds);
        List<LoyaltyInfoUi> loyaltiesInfo = uiModelToOrderModelConverter.convert(loyaltiesById, loyaltyInfo);
        modelAndView.addObject("userInfo", userInfo);
        modelAndView.addObject("tariffInfo", tariffInfo);
        modelAndView.addObject("servicesInfo", servicesInfo);
        modelAndView.addObject("homeInternetUserInfo", homeInternetInfo);
        modelAndView.addObject("homeInternetInfo", homeInternetById);
        modelAndView.addObject("loyaltiesInfo", loyaltiesInfo);
        modelAndView.setViewName("user/user-info");
        return modelAndView;
    }

    @RequestMapping(value = "/user-edit", method = RequestMethod.GET)
    public ModelAndView getUserEdit(ModelAndView modelAndView) {
        User userEdit = userService.findUserById(managerUtil.getAuthorizedUserId());
        modelAndView.addObject("userEdit", userEdit);
        modelAndView.setViewName("user/user-edit");

        return modelAndView;
    }

    @RequestMapping(value = "/replenish-balance", method = RequestMethod.GET)
    public ModelAndView replenishBalance(ModelAndView modelAndView) {
        User userEdit = userService.findUserById(managerUtil.getAuthorizedUserId());
        modelAndView.addObject("userEdit", userEdit);
        modelAndView.addObject("payload", new PayloadUi());
        modelAndView.setViewName("user/replenish-balance");

        return modelAndView;
    }

    @RequestMapping(value = "/complete-appointment", method = RequestMethod.GET)
    public ModelAndView completeAppointment(ModelAndView modelAndView) throws ParseException {
        Integer authorizedUserId = managerUtil.getAuthorizedUserId();
        UserHomeInternet homeInternetInfo = homeInternetService.findUserHomeInternetByUserId(managerUtil.getAuthorizedUserId());
        HomeInternet homeInternetById = homeInternetService.findHomeInternetById(homeInternetInfo.getHomeInternetId(), managerUtil.getAuthorizedUserId());
        if(homeInternetById.getUserHomeInternet() != null){
            User userInfo = userService.findUserById(managerUtil.getAuthorizedUserId());
            UserTariff userTariff = tariffService.findTariffByUserId(authorizedUserId);
            Tariff tariffInfo = tariffService.findTariffById(userTariff.getTariffId(), authorizedUserId);
            List<UserServices> servicesInfo = userAddsOnService.findServicesByUserId(authorizedUserId);
            loyaltyService.updateLoyaltyStatus();
            Iterable<UserLoyalty> loyaltyInfo = loyaltyService.findLoyaltyByUserId(authorizedUserId);
            List<Integer> loyaltyIds = new ArrayList<>();
            for (UserLoyalty loyaltyById : loyaltyInfo) {
                loyaltyIds.add(loyaltyById.getLoyaltyId());
            }
            Iterable<Loyalty> loyaltiesById = loyaltyService.findAllLoyaltyByIds(loyaltyIds);
            List<LoyaltyInfoUi> loyaltiesInfo = uiModelToOrderModelConverter.convert(loyaltiesById, loyaltyInfo);
            modelAndView.addObject("message", "There is no active appointment!");
            modelAndView.addObject("userInfo", userInfo);
            modelAndView.addObject("tariffInfo", tariffInfo);
            modelAndView.addObject("servicesInfo", servicesInfo);
            modelAndView.addObject("homeInternetUserInfo", homeInternetInfo);
            modelAndView.addObject("homeInternetInfo", homeInternetById);
            modelAndView.addObject("loyaltiesInfo", loyaltiesInfo);
            modelAndView.setViewName("user/user-info");
            return modelAndView;
        }
        modelAndView.addObject("userInternet", homeInternetInfo);
        modelAndView.addObject("homeInternet", homeInternetById);
        modelAndView.addObject("appointment", new AppointmentUi());
        modelAndView.setViewName("user/complete-appointment");

        return modelAndView;
    }

    @RequestMapping(value = "/submit-appointment")
    public ModelAndView submitAppointment(AppointmentUi appointmentUi) throws ParseException {
        ModelAndView modelAndView = new ModelAndView();
        Integer authorizedUserId = managerUtil.getAuthorizedUserId();
        homeInternetService.completeAppointment(appointmentUi.getHomeInternetId(), authorizedUserId);
        User userInfo = userService.findUserById(managerUtil.getAuthorizedUserId());
        UserTariff userTariff = tariffService.findTariffByUserId(authorizedUserId);
        Tariff tariffInfo = tariffService.findTariffById(userTariff.getTariffId(), authorizedUserId);
        List<UserServices> servicesInfo = userAddsOnService.findServicesByUserId(authorizedUserId);
        UserHomeInternet homeInternetInfo = homeInternetService.findUserHomeInternetByUserId(authorizedUserId);
        HomeInternet homeInternetById = homeInternetService.findHomeInternetById(homeInternetInfo.getHomeInternetId(), managerUtil.getAuthorizedUserId());
        loyaltyService.updateLoyaltyStatus();
        Iterable<UserLoyalty> loyaltyInfo = loyaltyService.findLoyaltyByUserId(authorizedUserId);
        List<Integer> loyaltyIds = new ArrayList<>();
        for (UserLoyalty loyaltyById : loyaltyInfo) {
            loyaltyIds.add(loyaltyById.getLoyaltyId());
        }
        Iterable<Loyalty> loyaltiesById = loyaltyService.findAllLoyaltyByIds(loyaltyIds);
        List<LoyaltyInfoUi> loyaltiesInfo = uiModelToOrderModelConverter.convert(loyaltiesById, loyaltyInfo);
        modelAndView.addObject("message", "Appointment was completed!");
        modelAndView.addObject("userInfo", userInfo);
        modelAndView.addObject("tariffInfo", tariffInfo);
        modelAndView.addObject("servicesInfo", servicesInfo);
        modelAndView.addObject("homeInternetUserInfo", homeInternetInfo);
        modelAndView.addObject("homeInternetInfo", homeInternetById);
        modelAndView.addObject("loyaltiesInfo", loyaltiesInfo);
        modelAndView.setViewName("user/user-info");

        return modelAndView;
    }

    @RequestMapping(value = "/payload")
    public ModelAndView payload(PayloadUi payloadUi) throws ParseException {
        ModelAndView modelAndView = new ModelAndView();
        userService.updateBalance(managerUtil.fillBalance(payloadUi.getSum()), managerUtil.getAuthorizedUserId());
        User userInfo = userService.findUserById(managerUtil.getAuthorizedUserId());
        Integer authorizedUserId = managerUtil.getAuthorizedUserId();
        UserTariff userTariff = tariffService.findTariffByUserId(authorizedUserId);
        Tariff tariffInfo = tariffService.findTariffById(userTariff.getTariffId(), authorizedUserId);
        List<UserServices> servicesInfo = userAddsOnService.findServicesByUserId(authorizedUserId);
        UserHomeInternet homeInternetInfo = homeInternetService.findUserHomeInternetByUserId(authorizedUserId);
        HomeInternet homeInternetById = homeInternetService.findHomeInternetById(homeInternetInfo.getHomeInternetId(), managerUtil.getAuthorizedUserId());
        loyaltyService.updateLoyaltyStatus();
        Iterable<UserLoyalty> loyaltyInfo = loyaltyService.findLoyaltyByUserId(authorizedUserId);
        List<Integer> loyaltyIds = new ArrayList<>();
        for (UserLoyalty loyaltyById : loyaltyInfo) {
            loyaltyIds.add(loyaltyById.getLoyaltyId());
        }
        Iterable<Loyalty> loyaltiesById = loyaltyService.findAllLoyaltyByIds(loyaltyIds);
        List<LoyaltyInfoUi> loyaltiesInfo = uiModelToOrderModelConverter.convert(loyaltiesById, loyaltyInfo);
        modelAndView.addObject("message", "Your balance was replenished!");
        modelAndView.addObject("userInfo", userInfo);
        modelAndView.addObject("tariffInfo", tariffInfo);
        modelAndView.addObject("servicesInfo", servicesInfo);
        modelAndView.addObject("homeInternetUserInfo", homeInternetInfo);
        modelAndView.addObject("homeInternetInfo", homeInternetById);
        modelAndView.addObject("loyaltiesInfo", loyaltiesInfo);
        modelAndView.setViewName("user/user-info");

        return modelAndView;
    }

    @RequestMapping(value = "/user-edit", method = RequestMethod.POST)
    public ModelAndView updateUserId(@Valid User user, BindingResult bindingResult, ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("user/user-edit");
        } else {
            User userEdit = userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("userEdit", userEdit);
            modelAndView.setViewName("user/user-edit");

        }
        return modelAndView;
    }
}
