package com.jtelecom.repositories.services;

import com.jtelecom.entities.services.ServiceRoaming;
import org.springframework.data.repository.CrudRepository;

public interface ServiceRoamingRepository extends CrudRepository<ServiceRoaming, Integer> {
    ServiceRoaming findServiceInternetByServiceId(Integer serviceId);
}
