package com.jtelecom.controllers;

import com.jtelecom.entities.history.OrderHistory;
import com.jtelecom.services.OrderHistoryService;
import com.jtelecom.utils.ManagerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class OrderHistoryController {

    private OrderHistoryService orderHistoryService;

    @Autowired
    public void setOrderHistoryService(OrderHistoryService orderHistoryService) {
        this.orderHistoryService = orderHistoryService;
    }

    @RequestMapping(value = {"/order-history"}, method = RequestMethod.GET)
    public ModelAndView orderHistoryAll(ModelAndView modelAndView) {
        Iterable<OrderHistory> orderHistory = orderHistoryService.findByUserId(ManagerUtil.getAuthorizedUserId());
        modelAndView.addObject("orderHistory", orderHistory);
        modelAndView.setViewName("order-history");
        return modelAndView;
    }
}
