package com.jtelecom.controllers;

import com.jtelecom.converters.UiModelToOrderModelConverter;
import com.jtelecom.entities.addsOn.UserServices;
import com.jtelecom.entities.services.ServiceCalls;
import com.jtelecom.exeption.UserFriendlyExeption;
import com.jtelecom.services.AddsOnAllService;
import com.jtelecom.services.UserAddsOnService;
import com.jtelecom.ui.AddsOnUiModel;
import com.jtelecom.utils.ManagerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Homepage controller.
 */
@Controller
@RequestMapping("user")
public class ServicesController {

    private AddsOnAllService serviceCallsService;
    private UserAddsOnService userAddsOnService;
    private UiModelToOrderModelConverter uiModelToOrderModelConverter;

    @Autowired
    public void setServiceCallsService(AddsOnAllService serviceCallsService) {
        this.serviceCallsService = serviceCallsService;
    }

    @Autowired
    public void setUserAddsOnService(UserAddsOnService userAddsOnService) {
        this.userAddsOnService = userAddsOnService;
    }

    @Autowired
    public void setUiModelToOrderModelConverter(UiModelToOrderModelConverter uiModelToOrderModelConverter) {
        this.uiModelToOrderModelConverter = uiModelToOrderModelConverter;
    }

    @RequestMapping(value = "/services", method = RequestMethod.GET)
    public String showAll(Model model) {
        Iterable<ServiceCalls> serviceAll = serviceCallsService.findAllServiceCalls();
        model.addAttribute("services", serviceAll);
        System.out.println("Returning services: " + serviceAll);
        return "services";
    }

    @RequestMapping(value = "service/{serviceId}", method = RequestMethod.GET)
    public String showServiceDetails(@PathVariable Integer serviceId, Model model) {
        ServiceCalls serviceById = serviceCallsService.findServiceCallsByServiceId(serviceId);
        model.addAttribute("service", serviceById);
        System.out.println("Returning service: " + serviceById);
        return "serviceDetails";
    }

    @RequestMapping(value = "activateServiceByType", method = RequestMethod.POST)
    public String activateService(@RequestParam("serviceId") Integer serviceId, @RequestParam("serviceType") String serviceType,
                                  Model model) throws UserFriendlyExeption {
        AddsOnUiModel convert = uiModelToOrderModelConverter.convert(serviceId, serviceType);
        UserServices userServices = userAddsOnService.saveServiceByType(convert, ManagerUtil.getPrincipalId());
        model.addAttribute("serviceAdd", userServices);
        System.out.println("Returning service: " + userServices);
        return "serviceAdded";
    }

    @RequestMapping(value = "deactivateServiceByType", method = RequestMethod.POST)
    public String deactivateServiceByType(@RequestParam("serviceId") Integer serviceId, @RequestParam("serviceType") String serviceType,
                                          Model model) throws UserFriendlyExeption {
        AddsOnUiModel convert = uiModelToOrderModelConverter.convert(serviceId, serviceType);
        userAddsOnService.deleteServiceByType(convert, ManagerUtil.getPrincipalId());
        model.addAttribute("serviceDelete", serviceId);
        System.out.println("Returning service: " + serviceId);
        return "serviceDeleted";
    }
}
