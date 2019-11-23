package com.jtelecom.repositories.loyalty;

import com.jtelecom.entities.loyalty.UserLoyalty;
import org.springframework.data.repository.CrudRepository;

public interface UserLoyaltyRepository extends CrudRepository<UserLoyalty, Long> {

    Iterable<UserLoyalty> findByUserId(Integer userId);
}
