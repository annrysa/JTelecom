package com.jtelecom.repositories.homeInternet;

import com.jtelecom.entities.homeInternet.HomeInternet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomeInternetRepository extends JpaRepository<HomeInternet, Long> {

    HomeInternet findByHomeInternetId(Integer homeInternetId);
}
