package com.example.project.location.controller;

import com.example.project.location.cache.LocationRedisTemplateService;
import com.example.project.location.dto.LocationDto;
import com.example.project.location.service.LocationRepositoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LocationController {

    private final LocationRepositoryService locationRepositoryService;
    private final LocationRedisTemplateService locationRedisTemplateService;

    // 데이터 초기 세팅을 위한 임시 메서드
    @GetMapping("/redis/save")
    public String save(){

        List<LocationDto> locationDtoList = locationRepositoryService.findAll()
                .stream().map(location -> LocationDto.builder()
                        .id(location.getId())
                        .locationName(location.getLocationName())
                        .locationAddress(location.getLocationAddress())
                        .latitude(location.getLatitude())
                        .longitude(location.getLongitude())
                        .build())
                .collect(Collectors.toList());

        locationDtoList.forEach(locationRedisTemplateService::save);

        return "success";
    }
}
