package com.jtelecom.repositories.history;

import com.jtelecom.entities.history.OrderHistory;
import org.springframework.data.repository.CrudRepository;

public interface OrderHistoryRepository extends CrudRepository<OrderHistory, Integer> {
    Iterable<OrderHistory> findAllByUserId(Integer userId);
}
