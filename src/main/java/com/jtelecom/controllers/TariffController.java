package com.jtelecom.controllers;

import com.jtelecom.converters.UiModelToOrderModelConverter;
import com.jtelecom.entities.tariff.Tariff;
import com.jtelecom.entities.tariff.UserTariff;
import com.jtelecom.services.*;
import com.jtelecom.utils.ManagerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Tariff controller.
 */
@Controller
@RequestMapping("user")
public class TariffController {

    private TariffService tariffService;
    private ManagerUtil managerUtil;
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setTariffService(TariffService tariffService) {
        this.tariffService = tariffService;
    }

    @Autowired
    public void setManagerUtil(ManagerUtil managerUtil) {
        this.managerUtil = managerUtil;
    }

    @RequestMapping(value = "/tariffs", method = RequestMethod.GET)
    public String showAll(Model model) {
        Iterable<Tariff> tariffs = tariffService.findAll(managerUtil.getAuthorizedUserId());
        model.addAttribute("tariffs", tariffs);
        System.out.println("Returning tariffs: " + tariffs);
        return "user/tariffs";
    }

    @RequestMapping(value = "/tariffs/{tariffId}", method = RequestMethod.GET)
    public String showServiceDetails(@PathVariable Integer tariffId, Model model) {
        Tariff tariffCallsById = tariffService.findTariffById(tariffId, managerUtil.getAuthorizedUserId());
        model.addAttribute("tariff", tariffCallsById);
        System.out.println("Returning tariff : " + tariffCallsById);
        return "user/tariffDetails";
    }

    @RequestMapping(value = "/activateTariff/{tariffId}")
    public String activateTariff(@PathVariable Integer tariffId,
                                  Model model) {

        Tariff tariff = tariffService.findTariffById(tariffId, managerUtil.getAuthorizedUserId());
        Integer authorizedUserBalance = managerUtil.getAuthorizedUserBalance();
        if (tariff.getPrice() > authorizedUserBalance) {
            model.addAttribute("tariff", tariff);
            System.out.println("Returning tariff : " + tariff);
            model.addAttribute("message", "Please top up your balance!");
            System.out.println("message: Please replenish your balance!");
            return "user/tariffDetails";
        }
        UserTariff oldTariff = tariffService.findTariffByUserId(managerUtil.getAuthorizedUserId());
        UserTariff userTariff = tariffService.updateTariffIdByUserId(tariffId, oldTariff, managerUtil.getAuthorizedUserId());
        Tariff tariffCallsById = tariffService.findTariffById(tariffId, managerUtil.getAuthorizedUserId());
        userService.updateLoyalty(managerUtil.fillLoyalty(tariffCallsById.getPrice()), managerUtil.getAuthorizedUserId());
        model.addAttribute("tariff", tariffCallsById);
        System.out.println("Returning tariff : " + tariffCallsById);
        model.addAttribute("message", "Tariff was activated!");
        System.out.println("Returning user tariff : " + userTariff);
        return "user/tariffDetails";
    }

    @RequestMapping(value = "/deactivateTariff/{tariffId}", method = RequestMethod.POST)
    public String deactivateServiceByType(@PathVariable Integer tariffId,
                                          Model model) {
        tariffService.delete(tariffId, managerUtil.getAuthorizedUserId());
        model.addAttribute("tariffDelete", tariffId);
        model.addAttribute("message", "Tariff was deactivated!");
        System.out.println("Returning tariff: " + tariffId);
        return "user/tariffDetails";
    }
}
