package com.jtelecom.repositories.loyalty;

import com.jtelecom.entities.loyalty.UserLoyalty;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserLoyaltyRepository extends CrudRepository<UserLoyalty, Long> {

    Iterable<UserLoyalty> findByUserId(Integer userId);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE UserLoyalty u SET u.isActive =:isActive WHERE u.id =:id")
    void updateUserLoyaltyIsActiveById(@Param("isActive") Integer isActive, @Param("id") Integer id);
}
