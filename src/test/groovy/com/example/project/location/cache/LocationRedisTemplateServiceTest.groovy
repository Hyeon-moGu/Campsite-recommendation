package com.example.project.location.cache

import com.example.project.AbstractIntegrationContainerBaseTest
import com.example.project.location.dto.LocationDto
import org.springframework.beans.factory.annotation.Autowired

class LocationRedisTemplateServiceTest extends AbstractIntegrationContainerBaseTest {

    @Autowired
    private LocationRedisTemplateService locationRedisTemplateService

    def setup() {
        locationRedisTemplateService.findAll()
            .forEach(dto -> {
                locationRedisTemplateService.delete(dto.getId())
            })
    }

    def "save success"() {
        given:
        String locationName = "name"
        String locationAddress = "address"
        LocationDto dto =
                LocationDto.builder()
                        .id(1L)
                        .locationName(locationName)
                        .locationAddress(locationAddress)
                        .build()

        when:
        locationRedisTemplateService.save(dto)
        List<LocationDto> result = locationRedisTemplateService.findAll()

        then:
        result.size() == 1
        result.get(0).id == 1L
        result.get(0).locationName == locationName
        result.get(0).locationAddress == locationAddress
    }

    def "success fail"() {
        given:
        LocationDto dto =
                LocationDto.builder()
                        .build()

        when:
        locationRedisTemplateService.save(dto)
        List<LocationDto> result = locationRedisTemplateService.findAll()

        then:
        result.size() == 0
    }

    def "delete"() {
        given:
        String locationName = "name"
        String locationAddress = "address"
        LocationDto dto =
                LocationDto.builder()
                        .id(1L)
                        .locationName(locationName)
                        .locationAddress(locationAddress)
                        .build()

        when:
        locationRedisTemplateService.save(dto)
        locationRedisTemplateService.delete(dto.getId())
        def result = locationRedisTemplateService.findAll()

        then:
        result.size() == 0
    }
}
