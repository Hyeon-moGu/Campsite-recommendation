package com.example.project.location.service;

import com.example.project.location.cache.LocationRedisTemplateService;
import com.example.project.location.dto.LocationDto;
import com.example.project.location.entitiy.Location;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class LocationSearchService {

    private final LocationRepositoryService locationRepositoryService;
    private final LocationRedisTemplateService locationRedisTemplateService;

    public List<LocationDto> searchLocationDtoList(){

        // redis
        List<LocationDto> locationDtoList = locationRedisTemplateService.findAll();
        if(!locationDtoList.isEmpty()) {
            log.info("redis findAll success");
            return locationDtoList;
        }

        // db
        return locationRepositoryService.findAll()
                .stream()
                .map(this::convertToLocationDto)
                .collect(Collectors.toList());
    }

    private LocationDto convertToLocationDto(Location location) {
        return LocationDto.builder()
                .id(location.getId())
                .locationName(location.getLocationName())
                .locationAddress(location.getLocationAddress())
                .latitude(location.getLatitude())
                .longitude(location.getLongitude())
                .build();
    }
}
