package com.jtelecom.repositories.services;

import com.jtelecom.entities.services.ServiceCalls;
import org.springframework.data.repository.CrudRepository;

public interface ServiceCallsRepository extends CrudRepository<ServiceCalls, Integer> {
    ServiceCalls findServiceCallsByServiceId(Integer serviceId);
}
