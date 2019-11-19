package com.jtelecom.services;

import com.jtelecom.entities.history.OrderHistory;

public interface OrderHistoryService {

    Iterable<OrderHistory> findByUserId(Integer userId);

    OrderHistory save(Integer userId, String details);
}
