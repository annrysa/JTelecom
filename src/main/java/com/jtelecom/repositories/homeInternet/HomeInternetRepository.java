package com.jtelecom.repositories.homeInternet;

import com.jtelecom.entities.homeInternet.HomeInternet;
import org.springframework.data.repository.CrudRepository;

public interface HomeInternetRepository extends CrudRepository<HomeInternet, Long> {

    HomeInternet findByHomeInternetId(Integer homeInternetId);
}
