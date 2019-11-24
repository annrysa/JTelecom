package com.jtelecom.services.impl;

import com.jtelecom.entities.history.OrderHistory;
import com.jtelecom.repositories.history.OrderHistoryRepository;
import com.jtelecom.services.OrderHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderHistoryServiceImpl implements OrderHistoryService {

    private OrderHistoryRepository orderHistoryRepository;

    @Autowired
    public void setOrderHistoryRepository(OrderHistoryRepository orderHistoryRepository) {
        this.orderHistoryRepository = orderHistoryRepository;
    }

    @Override
    public Iterable<OrderHistory> findByUserId(Integer userId) {
        return orderHistoryRepository.findAllByUserId(userId);
    }
}
