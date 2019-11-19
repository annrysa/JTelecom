package com.jtelecom.services;

import com.jtelecom.entities.services.ServiceCalls;
import com.jtelecom.entities.services.ServiceInternet;
import com.jtelecom.entities.services.ServiceRoaming;

public interface AddsOnAllService {
    ServiceCalls findServiceCallsByServiceId(Integer serviceId);

    ServiceInternet findServiceInternetByServiceId(Integer serviceId);

    ServiceRoaming findServiceRoamingByServiceId(Integer serviceId);

    Iterable<ServiceCalls> findAllServiceCalls();

    Iterable<ServiceInternet> findAllServiceInternet();

    Iterable<ServiceRoaming> findAllServiceRoaming();

}
