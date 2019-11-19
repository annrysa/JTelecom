package com.jtelecom.repositories.loyalty;

import com.jtelecom.entities.loyalty.Loyalty;
import org.springframework.data.repository.CrudRepository;

public interface LoyaltyRepository extends CrudRepository<Loyalty, Long> {

    Loyalty findByLoyaltyId(Integer loyaltyId);
}
