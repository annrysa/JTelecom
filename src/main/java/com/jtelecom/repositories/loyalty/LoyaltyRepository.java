package com.jtelecom.repositories.loyalty;

import com.jtelecom.entities.loyalty.Loyalty;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LoyaltyRepository extends CrudRepository<Loyalty, Long> {

    Loyalty findByLoyaltyId(Integer loyaltyId);

    Iterable<Loyalty> findAllByLoyaltyIdIn(List<Integer> loyaltyIds);
}
