package com.example.project.location.service

import com.example.project.location.cache.LocationRedisTemplateService
import com.example.project.location.entitiy.Location
import org.assertj.core.util.Lists
import spock.lang.Specification

class LocationSearchServiceTest extends Specification {

    private LocationSearchService locationSearchService

    private final LocationRepositoryService locationRepositoryService = Mock()
    private final LocationRedisTemplateService locationRedisTemplateService = Mock()

    private List<Location> locationList

    def setup() {
        locationSearchService = new LocationSearchService(locationRepositoryService, locationRedisTemplateService)

        locationList = Lists.newArrayList(
                Location.builder()
                        .id(1L)
                        .locationName("서창캠핑장")
                        .latitude(37.4386179)
                        .longitude(126.7551527)
                        .build(),
                Location.builder()
                        .id(2L)
                        .locationName("에제르파크")
                        .latitude(37.4570283)
                        .longitude(126.7719438)
                        .build())
    }

    def "레디스 장애시 DB를 이용하여 캠핑장 데이터 조회"() {
        when:
        locationRedisTemplateService.findAll() >> []
        locationRepositoryService.findAll() >> locationList

        def result = locationSearchService.searchLocationDtoList()

        then:
        result.size() == 2
    }
}
