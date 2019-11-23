package com.jtelecom.controllers;

import com.jtelecom.entities.tariff.Tariff;
import com.jtelecom.entities.tariff.UserTariff;
import com.jtelecom.exeption.UserFriendlyExeption;
import com.jtelecom.services.TariffService;
import com.jtelecom.utils.ManagerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Tariff controller.
 */
@Controller
@RequestMapping("user")
public class TariffController {

    private TariffService tariffService;

    @Autowired
    public void setTariffService(TariffService tariffService) {
        this.tariffService = tariffService;
    }

    @RequestMapping(value = "/tariffs", method = RequestMethod.GET)
    public String showAll(Model model) {
        Iterable<Tariff> tariffs = tariffService.findAll();
        model.addAttribute("tariffs", tariffs);
        System.out.println("Returning tariffs: " + tariffs);
        return "user/tariffs";
    }

    @RequestMapping(value = "/tariffs/{tariffId}", method = RequestMethod.GET)
    public String showServiceDetails(@PathVariable Integer tariffId, Model model) {
        Tariff tariffCallsById = tariffService.findTariffById(tariffId);
        model.addAttribute("tariff", tariffCallsById);
        System.out.println("Returning tariff : " + tariffCallsById);
        return "user/tariffDetails";
    }

    @RequestMapping(value = "/activateTariff/{tariffId}", method = RequestMethod.POST)
    public String activateService(@PathVariable Integer tariffId,
                                  Model model) throws UserFriendlyExeption {
        UserTariff userTariff = tariffService.save(tariffId, ManagerUtil.getAuthorizedUserId());
        model.addAttribute("tariffAdd", userTariff);
        System.out.println("Returning tariff: " + userTariff);
        return "user/tariffAdded";
    }

    @RequestMapping(value = "/deactivateTariff/{tariffId}", method = RequestMethod.POST)
    public String deactivateServiceByType(@PathVariable Integer tariffId,
                                          Model model) throws UserFriendlyExeption {
        tariffService.delete(tariffId, ManagerUtil.getAuthorizedUserId());
        model.addAttribute("tariffDelete", tariffId);
        System.out.println("Returning tariff: " + tariffId);
        return "user/tariffDeleted";
    }
}
