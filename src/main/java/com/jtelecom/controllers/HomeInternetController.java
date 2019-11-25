package com.jtelecom.controllers;

import com.jtelecom.entities.homeInternet.HomeInternet;
import com.jtelecom.entities.homeInternet.UserHomeInternet;
import com.jtelecom.services.HomeInternetService;
import com.jtelecom.utils.ManagerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class HomeInternetController {

    private HomeInternetService homeInternetService;
    private ManagerUtil managerUtil;

    @Autowired
    public void setManagerUtil(ManagerUtil managerUtil) {
        this.managerUtil = managerUtil;
    }

    @Autowired
    public void setHomeInternetService(HomeInternetService homeInternetService) {
        this.homeInternetService = homeInternetService;
    }

    @RequestMapping(value = {"/home-internet"}, method = RequestMethod.GET)
    public ModelAndView homeInternetAll(ModelAndView modelAndView) {
        Iterable<HomeInternet> homeInternetInfo = homeInternetService.findAll(managerUtil.getAuthorizedUserId());
        modelAndView.addObject("homeInternetInfo", homeInternetInfo);
        System.out.println("homeInternetInfo " + homeInternetInfo);
        modelAndView.setViewName("user/home-internet");
        return modelAndView;
    }

    @RequestMapping(value = {"/home-internet/{homeInternetId}"}, method = RequestMethod.GET)
    public ModelAndView homeInternetDetails(@PathVariable Integer homeInternetId, ModelAndView modelAndView) {
        HomeInternet homeInternetDetailsInfo = homeInternetService
                .findHomeInternetById(homeInternetId, managerUtil.getAuthorizedUserId());
        modelAndView.addObject("homeInternetInfo", homeInternetDetailsInfo);
        modelAndView.setViewName("user/home-internet-details");
        return modelAndView;
    }

    @RequestMapping(value = "/appointment-home-internet/{homeInternetId}")
    public ModelAndView appointmentHomeInternet(@PathVariable Integer homeInternetId) {
        ModelAndView modelAndView = new ModelAndView();
        HomeInternet homeInternetById = homeInternetService
                .findHomeInternetById(homeInternetId, managerUtil.getAuthorizedUserId());
        modelAndView.addObject("homeInternetInfo", homeInternetById);
        modelAndView.addObject("appointmentUi", new UserHomeInternet());
        modelAndView.setViewName("user/appointment-home-internet");

        return modelAndView;
    }

    @RequestMapping(value = "/save-home-internet")
    public ModelAndView saveHomeInternet(UserHomeInternet userHomeInternet) {
        ModelAndView modelAndView = new ModelAndView();
        userHomeInternet.setUserId(managerUtil.getAuthorizedUserId());
        userHomeInternet.setIsActive(0);
        UserHomeInternet userHomeInternetInfo = homeInternetService.save(userHomeInternet);
        HomeInternet homeInternetDetailsInfo = homeInternetService
                .findHomeInternetById(userHomeInternetInfo.getHomeInternetId(), managerUtil.getAuthorizedUserId());
        modelAndView.addObject("message", "Home Internet has been activated");
        modelAndView.addObject("homeInternetInfo", homeInternetDetailsInfo);
        modelAndView.setViewName("user/home-internet-details");

        return modelAndView;
    }
}
