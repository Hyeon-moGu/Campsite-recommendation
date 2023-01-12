package com.example.project.location.service;

import com.example.project.location.entitiy.Location;
import com.example.project.location.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;


import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class LocationRepositoryService {
    private final LocationRepository locationRepository;

    // self invocation
    public void bar(List<Location> locationList){
        log.info("bar CurrentTransactionName: ", TransactionSynchronizationManager.getCurrentTransactionName());
        foo(locationList);
    }

    // self invocation
    @Transactional
    public void foo(List<Location> locationList){
        log.info("foo CurrentTransactionName: ", TransactionSynchronizationManager.getCurrentTransactionName());
        locationList.forEach(location -> {
            locationRepository.save(location);
            throw new RuntimeException("error"); // exception
        });
    }

    // read only test
    @Transactional(readOnly = true)
    public void startReadOnlyMethod(Long id) {
        locationRepository.findById(id).ifPresent(location ->
                location.changeLocationAddress("인천 광역시 미추홀구 소성로 136"));
    }


    @Transactional
    public void updateAddress(Long id, String address){
        Location entity = locationRepository.findById(id).orElse(null);

        if(Objects.isNull(entity)){
            log.error("[LocationRepository updateAddress] not found id: {}", id);
            return;
        }

        entity.changeLocationAddress(address);
    }

    // for test
    public void updateAddressWithoutTransactional(Long id, String address){
        Location entity = locationRepository.findById(id).orElse(null);

        if(Objects.isNull(entity)){
            log.error("[LocationRepository updateAddress] not found id: {}", id);
            return;
        }

        entity.changeLocationAddress(address);
    }

}
