package com.jtelecom.controllers;

import com.jtelecom.converters.UiModelToOrderModelConverter;
import com.jtelecom.entities.addsOn.UserServiceCalls;
import com.jtelecom.entities.addsOn.UserServiceInternet;
import com.jtelecom.entities.addsOn.UserServiceRoaming;
import com.jtelecom.entities.services.ServiceCalls;
import com.jtelecom.entities.services.ServiceInternet;
import com.jtelecom.entities.services.ServiceRoaming;
import com.jtelecom.exeption.UserFriendlyExeption;
import com.jtelecom.services.AddsOnAllService;
import com.jtelecom.services.UserAddsOnService;
import com.jtelecom.services.UserService;
import com.jtelecom.utils.ManagerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashSet;
import java.util.Set;

/**
 * Adds-on controller.
 */
@Controller
@RequestMapping("user")
public class ServicesController {

    private AddsOnAllService serviceCallsService;
    private UserAddsOnService userAddsOnService;
    private UiModelToOrderModelConverter uiModelToOrderModelConverter;
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
        Iterable<ServiceCalls> serviceCalls = serviceCallsService.findAllServiceCalls(managerUtil.getAuthorizedUserId());
        Iterable<ServiceRoaming> serviceRoamings = serviceCallsService.findAllServiceRoaming(managerUtil.getAuthorizedUserId());
        Iterable<ServiceInternet> serviceInternets = serviceCallsService.findAllServiceInternet(managerUtil.getAuthorizedUserId());
        model.addAttribute("serviceCalls", serviceCalls);
        model.addAttribute("serviceRoamings", serviceRoamings);
        model.addAttribute("serviceInternets", serviceInternets);
        System.out.println("Returning serviceCalls: " + serviceCalls);
        System.out.println("Returning serviceRoamings: " + serviceRoamings);
        System.out.println("Returning serviceInternets: " + serviceInternets);
        return "user/services";
    }

    @RequestMapping(value = "service-calls/{serviceId}")
    public String showServiceCallsDetails(@PathVariable Integer serviceId, Model model) {
        ServiceCalls serviceById = serviceCallsService.findServiceCallsByServiceId(serviceId, managerUtil.getAuthorizedUserId());
        model.addAttribute("serviceCalls", serviceById);
        System.out.println("Returning service calls : " + serviceById);
        return "user/service-calls-details";
    }

    @RequestMapping(value = "service-internet/{serviceId}")
    public String showServiceInternetDetails(@PathVariable Integer serviceId, Model model) {
        ServiceInternet serviceById = serviceCallsService.findServiceInternetByServiceId(serviceId, managerUtil.getAuthorizedUserId());
        model.addAttribute("serviceInternet", serviceById);
        System.out.println("Returning service internet : " + serviceById);
        return "user/service-internet-details";
    }

    @RequestMapping(value = "service-roaming/{serviceId}")
    public String showServiceRoamingDetails(@PathVariable Integer serviceId, Model model) {
        ServiceRoaming serviceById = serviceCallsService.findServiceRoamingByServiceId(serviceId, managerUtil.getAuthorizedUserId());
        model.addAttribute("serviceRoaming", serviceById);
        System.out.println("Returning service roaming : " + serviceById);
        return "user/service-roaming-details";
    }

    @RequestMapping(value = "activate-service-internet/{serviceId}")
    public String activateServiceInternet(@PathVariable Integer serviceId,
                                          Model model) throws UserFriendlyExeption {
        ServiceInternet service = serviceCallsService.findServiceInternetByServiceId(serviceId, managerUtil.getAuthorizedUserId());
        if (service.getPrice() > managerUtil.getAuthorizedUserBalance()) {
            model.addAttribute("serviceInternet", service);
            System.out.println("Returning service internet : " + service);
            model.addAttribute("message", "Please top up your balance!");
            System.out.println("message: Please top up your balance!");
            return "user/service-internet-details";
        }
        UserServiceInternet userServices = userAddsOnService.saveServiceInternet(serviceId, managerUtil.getAuthorizedUserId());
        ServiceInternet serviceById = serviceCallsService.findServiceInternetByServiceId(serviceId, managerUtil.getAuthorizedUserId());
        Set<UserServiceInternet> si = new HashSet<>();
        si.add(userServices);
        serviceById.setUserServiceInternets(si);
        userService.updateLoyalty(managerUtil.fillLoyalty(serviceById.getPrice()), managerUtil.getAuthorizedUserId());
        model.addAttribute("serviceInternet", serviceById);
        System.out.println("Returning service internet : " + userServices);
        return "user/service-internet-details";
    }

    @RequestMapping(value = "activate-service-calls/{serviceId}")
    public String activateServiceCalls(@PathVariable Integer serviceId,
                                       Model model) throws UserFriendlyExeption {
        ServiceCalls service = serviceCallsService.findServiceCallsByServiceId(serviceId, managerUtil.getAuthorizedUserId());
        if (service.getPrice() > managerUtil.getAuthorizedUserBalance()) {
            model.addAttribute("serviceCalls", service);
            System.out.println("Returning service calls : " + service);
            model.addAttribute("message", "Please top up your balance!");
            System.out.println("message: Please replenish your balance!");
            return "user/service-calls-details";
        }
        UserServiceCalls userServiceCalls = userAddsOnService.saveServiceCalls(serviceId, managerUtil.getAuthorizedUserId());
        ServiceCalls serviceById = serviceCallsService.findServiceCallsByServiceId(serviceId, managerUtil.getAuthorizedUserId());
        Set<UserServiceCalls> si = new HashSet<>();
        si.add(userServiceCalls);
        serviceById.setUserServiceCalls(si);
        userService.updateLoyalty(managerUtil.fillLoyalty(serviceById.getPrice()), managerUtil.getAuthorizedUserId());
        model.addAttribute("serviceCalls", serviceById);
        System.out.println("Returning service calls : " + userServiceCalls);
        return "user/service-calls-details";
    }

    @RequestMapping(value = "activate-service-roaming/{serviceId}")
    public String activateServiceRoaming(@PathVariable Integer serviceId,
                                         Model model) throws UserFriendlyExeption {
        ServiceRoaming service = serviceCallsService.findServiceRoamingByServiceId(serviceId, managerUtil.getAuthorizedUserId());
        if (service.getPrice() > managerUtil.getAuthorizedUserBalance()) {
            model.addAttribute("serviceRoaming", service);
            System.out.println("Returning service roaming : " + service);
            model.addAttribute("message", "Please top up your balance!");
            System.out.println("message: Please replenish your balance!");
            return "user/service-roaming-details";
        }
        UserServiceRoaming userServiceRoaming = userAddsOnService.saveServiceRoaming(serviceId, managerUtil.getAuthorizedUserId());
        ServiceRoaming serviceById = serviceCallsService.findServiceRoamingByServiceId(serviceId, managerUtil.getAuthorizedUserId());
        Set<UserServiceRoaming> si = new HashSet<>();
        si.add(userServiceRoaming);
        serviceById.setUserServiceRoamings(si);
        userService.updateLoyalty(managerUtil.fillLoyalty(serviceById.getPrice()), managerUtil.getAuthorizedUserId());
        model.addAttribute("serviceRoaming", serviceById);
        System.out.println("Returning service roaming : " + serviceById);
        return "user/service-roaming-details";
    }

    @RequestMapping(value = "deactivate-service-internet/{serviceId}")
    public String deactivateServiceInternet(@PathVariable Integer serviceId,
                                            Model model) throws UserFriendlyExeption {
        userAddsOnService.deleteServiceInternet(serviceId, managerUtil.getAuthorizedUserId());
        ServiceInternet serviceInternet = serviceCallsService.findServiceInternetByServiceId(serviceId, managerUtil.getAuthorizedUserId());
        model.addAttribute("serviceInternet", serviceInternet);
        System.out.println("Returning service internet : " + serviceInternet);
        return "user/service-internet-details";
    }

    @RequestMapping(value = "deactivate-service-calls/{serviceId}")
    public String deactivateServiceCalls(@PathVariable Integer serviceId,
                                         Model model) throws UserFriendlyExeption {
        userAddsOnService.deleteServiceCalls(serviceId, managerUtil.getAuthorizedUserId());
        ServiceCalls serviceCalls = serviceCallsService.findServiceCallsByServiceId(serviceId, managerUtil.getAuthorizedUserId());
        model.addAttribute("serviceCalls", serviceCalls);
        System.out.println("Returning service: " + serviceCalls);
        return "user/service-calls-details";
    }

    @RequestMapping(value = "deactivate-service-roaming/{serviceId}")
    public String deactivateServiceRoaming(@PathVariable Integer serviceId,
                                           Model model) throws UserFriendlyExeption {
        userAddsOnService.deleteServiceRoaming(serviceId, managerUtil.getAuthorizedUserId());
        ServiceRoaming serviceRoaming = serviceCallsService.findServiceRoamingByServiceId(serviceId, managerUtil.getAuthorizedUserId());
        model.addAttribute("serviceRoaming", serviceRoaming);
        System.out.println("Returning service roaming: " + serviceRoaming);
        return "user/service-roaming-details";
    }
}
