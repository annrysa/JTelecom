package com.jtelecom.services;

import com.jtelecom.entities.services.ServiceCalls;
import com.jtelecom.entities.services.ServiceInternet;
import com.jtelecom.entities.services.ServiceRoaming;

public interface AddsOnAllService {
    ServiceCalls findServiceCallsByServiceId(Integer serviceId, Integer userId);

    ServiceInternet findServiceInternetByServiceId(Integer serviceId, Integer userId);

    ServiceRoaming findServiceRoamingByServiceId(Integer serviceId, Integer userId);

    Iterable<ServiceCalls> findAllServiceCalls(Integer userId);

    Iterable<ServiceInternet> findAllServiceInternet(Integer userId);

    Iterable<ServiceRoaming> findAllServiceRoaming(Integer userId);

}
