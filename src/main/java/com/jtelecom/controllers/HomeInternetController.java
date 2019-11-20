package com.jtelecom.controllers;

import com.jtelecom.entities.homeInternet.HomeInternet;
import com.jtelecom.entities.homeInternet.UserHomeInternet;
import com.jtelecom.services.HomeInternetService;
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

    @Autowired
    public void setHomeInternetService(HomeInternetService homeInternetService) {
        this.homeInternetService = homeInternetService;
    }

    @RequestMapping(value = {"/home-internet"}, method = RequestMethod.GET)
    public ModelAndView homeInternetAll(ModelAndView modelAndView) {
        Iterable<HomeInternet> homeInternetInfo = homeInternetService.findAll();
        modelAndView.addObject("homeInternetInfo", homeInternetInfo);
        modelAndView.setViewName("home-internet");
        return modelAndView;
    }

    @RequestMapping(value = {"/home-internet/{homeInternetId}"}, method = RequestMethod.GET)
    public ModelAndView homeInternetDetails(@PathVariable Integer homeInternetId, ModelAndView modelAndView) {
        HomeInternet homeInternetDetailsInfo = homeInternetService.findHomeInternetById(homeInternetId);
        modelAndView.addObject("homeInternetDetailsInfo", homeInternetDetailsInfo);
        modelAndView.setViewName("home-internet-details");
        return modelAndView;
    }

    @RequestMapping(value = "/save-home-internet", method = RequestMethod.POST)
    public ModelAndView saveHomeInternet(UserHomeInternet userHomeInternet) {
        ModelAndView modelAndView = new ModelAndView();
        UserHomeInternet userHomeInternetInfo = homeInternetService.save(userHomeInternet);
        modelAndView.addObject("successMessage", "Home Internet has been activated");
        modelAndView.addObject("userHomeInternetInfo", userHomeInternetInfo);
        modelAndView.setViewName("home-internet-details");

        return modelAndView;
    }
}
