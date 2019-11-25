package com.jtelecom.controllers;

import com.jtelecom.converters.UiModelToOrderModelConverter;
import com.jtelecom.entities.addsOn.UserServiceInternet;
import com.jtelecom.entities.addsOn.UserServiceRoaming;
import com.jtelecom.entities.addsOn.UserServices;
import com.jtelecom.entities.services.ServiceCalls;
import com.jtelecom.entities.services.ServiceInternet;
import com.jtelecom.entities.services.ServiceRoaming;
import com.jtelecom.exeption.UserFriendlyExeption;
import com.jtelecom.services.AddsOnAllService;
import com.jtelecom.services.UserAddsOnService;
import com.jtelecom.utils.ManagerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping(value = "/user/services", method = RequestMethod.GET)
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

    @RequestMapping(value = "user/service-calls/{serviceId}")
    public String showServiceCallsDetails(@PathVariable Integer serviceId, ModelAndView model) {
        ServiceCalls serviceById = serviceCallsService.findServiceCallsByServiceId(serviceId, managerUtil.getAuthorizedUserId());
        model.addObject("service", serviceById);
        System.out.println("Returning service calls : " + serviceById);
        return "user/service-calls-details";
    }

    @RequestMapping(value = "user/service-internet/{serviceId}")
    public String showServiceInternetDetails(@PathVariable Integer serviceId, ModelAndView model) {
        ServiceInternet serviceById = serviceCallsService.findServiceInternetByServiceId(serviceId, managerUtil.getAuthorizedUserId());
        model.addObject("service", serviceById);
        System.out.println("Returning service internet : " + serviceById);
        return "user/service-internet-details";
    }

    @RequestMapping(value = "user/service-roaming/{serviceId}")
    public String showServiceRoamingDetails(@PathVariable Integer serviceId, ModelAndView model) {
        ServiceRoaming serviceById = serviceCallsService.findServiceRoamingByServiceId(serviceId, managerUtil.getAuthorizedUserId());
        model.addObject("service", serviceById);
        System.out.println("Returning service roaming : " + serviceById);
        return "user/service-roaming-details";
    }

    @RequestMapping(value = "user/activate-service-internet/{serviceId}")
    public String activateServiceInternet(@PathVariable Integer serviceId,
                                          Model model) throws UserFriendlyExeption {
        UserServiceInternet userServices = userAddsOnService.saveServiceInternet(serviceId, managerUtil.getAuthorizedUserId());
        ServiceInternet serviceById = serviceCallsService.findServiceInternetByServiceId(serviceId, managerUtil.getAuthorizedUserId());
        Set<UserServiceInternet> si = new HashSet<>();
        si.add(userServices);
        serviceById.setUserServiceInternets(si);
        model.addAttribute("serviceAdd", userServices);
        System.out.println("Returning service internet : " + userServices);
        return "user/service-internet-added";
    }

    @RequestMapping(value = "user/activate-service-calls/{serviceId}")
    public String activateServiceCalls(@PathVariable Integer serviceId,
                                       Model model) throws UserFriendlyExeption {
        UserServices userServices = userAddsOnService.saveServiceCalls(serviceId, managerUtil.getAuthorizedUserId());
        model.addAttribute("serviceAdd", userServices);
        System.out.println("Returning service calls : " + userServices);
        return "user/service-calls-added";
    }

    @RequestMapping(value = "user/activate-service-roaming/{serviceId}")
    public String activateServiceRoaming(@PathVariable Integer serviceId,
                                         Model model) throws UserFriendlyExeption {
        UserServiceRoaming userServices = userAddsOnService.saveServiceRoaming(serviceId, managerUtil.getAuthorizedUserId());
        model.addAttribute("serviceAdd", userServices);
        System.out.println("Returning service roaming : " + userServices);
        return "user/service-roaming-added";
    }

    @RequestMapping(value = "user/deactivate-service-internet/{serviceId}")
    public String deactivateServiceInternet(@PathVariable Integer serviceId,
                                            Model model) throws UserFriendlyExeption {
        // TODO: 26.11.2019
//        userAddsOnService.deleteServiceByType(convert, managerUtil.getAuthorizedUserId());
        model.addAttribute("serviceDelete", serviceId);
        System.out.println("Returning service internet : " + serviceId);
        return "user/service-internet-deactivated";
    }

    @RequestMapping(value = "user/deactivate-service-calls/{serviceId}")
    public String deactivateServiceCalls(@PathVariable Integer serviceId,
                                         Model model) throws UserFriendlyExeption {
        // TODO: 26.11.2019
//        userAddsOnService.deleteServiceByType(convert, managerUtil.getAuthorizedUserId());
        model.addAttribute("serviceDelete", serviceId);
        System.out.println("Returning service: " + serviceId);
        return "user/service-calls-deactivated";
    }

    @RequestMapping(value = "user/deactivate-service-roaming/{serviceId}")
    public String deactivateServiceRoaming(@PathVariable Integer serviceId,
                                           Model model) throws UserFriendlyExeption {
        // TODO: 26.11.2019
//        userAddsOnService.deleteServiceByType(convert, managerUtil.getAuthorizedUserId());
        model.addAttribute("serviceDelete", serviceId);
        System.out.println("Returning service roaming: " + serviceId);
        return "user/service-roaming-deactivated";
    }
}
