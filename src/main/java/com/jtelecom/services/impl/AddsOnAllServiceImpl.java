package com.jtelecom.services.impl;

import com.jtelecom.entities.addsOn.UserServiceCalls;
import com.jtelecom.entities.addsOn.UserServiceInternet;
import com.jtelecom.entities.addsOn.UserServiceRoaming;
import com.jtelecom.entities.services.ServiceCalls;
import com.jtelecom.entities.services.ServiceInternet;
import com.jtelecom.entities.services.ServiceRoaming;
import com.jtelecom.repositories.addsOn.UserServiceCallsRepository;
import com.jtelecom.repositories.addsOn.UserServiceInternetRepository;
import com.jtelecom.repositories.addsOn.UserServiceRoamingRepository;
import com.jtelecom.repositories.services.ServiceCallsRepository;
import com.jtelecom.repositories.services.ServiceInternetRepository;
import com.jtelecom.repositories.services.ServiceRoamingRepository;
import com.jtelecom.services.AddsOnAllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AddsOnAllServiceImpl implements AddsOnAllService {

    private ServiceCallsRepository serviceCallsRepository;
    private ServiceInternetRepository serviceInternetRepository;
    private ServiceRoamingRepository serviceRoamingRepository;
    private UserServiceCallsRepository userServiceCallsRepository;
    private UserServiceInternetRepository userServiceInternetRepository;
    private UserServiceRoamingRepository userServiceRoamingRepository;

    @Autowired
    public void setUserServiceCallsRepository(UserServiceCallsRepository userServiceCallsRepository) {
        this.userServiceCallsRepository = userServiceCallsRepository;
    }

    @Autowired
    public void setUserServiceInternetRepository(UserServiceInternetRepository userServiceInternetRepository) {
        this.userServiceInternetRepository = userServiceInternetRepository;
    }

    @Autowired
    public void setUserServiceRoamingRepository(UserServiceRoamingRepository userServiceRoamingRepository) {
        this.userServiceRoamingRepository = userServiceRoamingRepository;
    }

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
    public ServiceCalls findServiceCallsByServiceId(Integer serviceId, Integer userId) {
        ServiceCalls sc = serviceCallsRepository.findServiceCallsByServiceId(serviceId);
        UserServiceCalls usc = userServiceCallsRepository.findByServiceIdAndUserIdAndIsActive(sc.getServiceId(), userId, 1);
        Set<UserServiceCalls> userServiceCallsSet = new HashSet<>();
        userServiceCallsSet.add(usc);
        sc.setUserServiceCalls(usc == null ? null : userServiceCallsSet);
        return sc;
    }

    @Override
    public ServiceInternet findServiceInternetByServiceId(Integer serviceId, Integer userId) {
        ServiceInternet si = serviceInternetRepository.findServiceInternetByServiceId(serviceId);
        UserServiceInternet usi = userServiceInternetRepository.findByServiceIdAndUserIdAndIsActive(si.getServiceId(), userId, 1);
        Set<UserServiceInternet> userServiceInternets = new HashSet<>();
        userServiceInternets.add(usi);
        si.setUserServiceInternets(usi == null ? null : userServiceInternets);
        return si;
    }

    @Override
    public ServiceRoaming findServiceRoamingByServiceId(Integer serviceId, Integer userId) {
        ServiceRoaming sr = serviceRoamingRepository.findServiceInternetByServiceId(serviceId);
        UserServiceRoaming usr = userServiceRoamingRepository.findByServiceIdAndUserIdAndIsActive(sr.getServiceId(), userId, 1);
        Set<UserServiceRoaming> userServiceInternets = new HashSet<>();
        userServiceInternets.add(usr);
        sr.setUserServiceRoamings(usr == null ? null : userServiceInternets);
        return sr;
    }

    @Override
    public Iterable<ServiceCalls> findAllServiceCalls(Integer userId) {
        List<ServiceCalls> serviceCalls = new ArrayList<>();
        Iterable<ServiceCalls> all = serviceCallsRepository.findAll();
        all.forEach(serviceCalls::add);

        for (ServiceCalls sc : serviceCalls) {
            UserServiceCalls usc = userServiceCallsRepository.findByServiceIdAndUserIdAndIsActive(sc.getServiceId(), userId, 1);
            Set<UserServiceCalls> userServiceCallsSet = new HashSet<>();
            userServiceCallsSet.add(usc);
            sc.setUserServiceCalls(usc == null ? null : userServiceCallsSet);
        }
        return serviceCalls;
    }

    @Override
    public Iterable<ServiceInternet> findAllServiceInternet(Integer userId) {
        List<ServiceInternet> serviceInternets = new ArrayList<>();
        Iterable<ServiceInternet> all = serviceInternetRepository.findAll();
        all.forEach(serviceInternets::add);

        for (ServiceInternet si : serviceInternets) {
            UserServiceInternet usi = userServiceInternetRepository.findByServiceIdAndUserIdAndIsActive(si.getServiceId(), userId, 1);
            Set<UserServiceInternet> userServiceInternets = new HashSet<>();
            userServiceInternets.add(usi);
            si.setUserServiceInternets(usi == null ? null : userServiceInternets);
        }
        return serviceInternets;
    }

    @Override
    public Iterable<ServiceRoaming> findAllServiceRoaming(Integer userId) {
        List<ServiceRoaming> serviceRoamings = new ArrayList<>();
        Iterable<ServiceRoaming> all = serviceRoamingRepository.findAll();
        all.forEach(serviceRoamings::add);

        for (ServiceRoaming sr : serviceRoamings) {
            UserServiceRoaming usr = userServiceRoamingRepository.findByServiceIdAndUserIdAndIsActive(sr.getServiceId(), userId, 1);
            Set<UserServiceRoaming> userServiceInternets = new HashSet<>();
            userServiceInternets.add(usr);
            sr.setUserServiceRoamings(usr == null ? null : userServiceInternets);
        }
        return serviceRoamings;
    }
}
