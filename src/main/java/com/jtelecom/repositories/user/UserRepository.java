package com.jtelecom.repositories.user;

import com.jtelecom.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    User findById(Integer userId);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE User u SET u.balance =:balance WHERE u.id =:userId")
    void updateBalance(@Param("userId") Integer userId, @Param("balance") Integer balance);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE User u SET u.loyalty =:loyalty WHERE u.id =:userId")
    void updateLoyalty(@Param("userId") Integer userId, @Param("loyalty") Integer loyalty);
}
