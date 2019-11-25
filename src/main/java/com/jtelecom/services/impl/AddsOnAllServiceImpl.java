package com.jtelecom.services.impl;

import com.jtelecom.entities.services.ServiceCalls;
import com.jtelecom.entities.services.ServiceInternet;
import com.jtelecom.entities.services.ServiceRoaming;
import com.jtelecom.repositories.services.ServiceCallsRepository;
import com.jtelecom.repositories.services.ServiceInternetRepository;
import com.jtelecom.repositories.services.ServiceRoamingRepository;
import com.jtelecom.services.AddsOnAllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddsOnAllServiceImpl implements AddsOnAllService {

    private ServiceCallsRepository serviceCallsRepository;
    private ServiceInternetRepository serviceInternetRepository;
    private ServiceRoamingRepository serviceRoamingRepository;

    @Autowired
    public void setServiceCallsRepository(ServiceCallsRepository serviceCallsRepository) {
        this.serviceCallsRepository = serviceCallsRepository;
    }

    @Autowired
    public void setServiceInternetRepository(ServiceInternetRepository serviceInternetRepository) {
        this.serviceInternetRepository = serviceInternetRepository;
    }

    @Autowired
    public void setServiceRoamingRepository(ServiceRoamingRepository serviceRoamingRepository) {
        this.serviceRoamingRepository = serviceRoamingRepository;
    }

    @Override
    public ServiceCalls findServiceCallsByServiceId(Integer serviceId) {
        return serviceCallsRepository.findServiceCallsByServiceId(serviceId);
    }

    @Override
    public ServiceInternet findServiceInternetByServiceId(Integer serviceId) {
        return serviceInternetRepository.findServiceInternetByServiceId(serviceId);
    }

    @Override
    public ServiceRoaming findServiceRoamingByServiceId(Integer serviceId) {
        return serviceRoamingRepository.findServiceInternetByServiceId(serviceId);
    }

    @Override
    public Iterable<ServiceCalls> findAllServiceCalls() {

        return serviceCallsRepository.findAll();
    }

    @Override
    public Iterable<ServiceInternet> findAllServiceInternet() {
        return serviceInternetRepository.findAll();
    }

    @Override
    public Iterable<ServiceRoaming> findAllServiceRoaming() {
        return serviceRoamingRepository.findAll();
    }
}
