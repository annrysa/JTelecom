package com.jtelecom.repositories.services;

import com.jtelecom.entities.services.ServiceInternet;
import org.springframework.data.repository.CrudRepository;

public interface ServiceInternetRepository extends CrudRepository<ServiceInternet, Integer> {
    ServiceInternet findServiceInternetByServiceId(Integer serviceId);
}
