package com.example.project.location.repository

import com.example.project.AbstractIntegrationContainerBaseTest
import com.example.project.location.entitiy.Location
import org.springframework.beans.factory.annotation.Autowired

class LocationRepositoryTest extends AbstractIntegrationContainerBaseTest {

    @Autowired
    private LocationRepository locationRepository;

    def setup() {
        locationRepository.deleteAll()
    }

    def "LocationRepository save"() {
        given:
        String address = "인천 광역시 미추홀구 학익1동"
        String name = "명성 약국"
        double latitude = 37.4429272188
        double longitude = 126.6632363094

        def location = Location.builder()
                .locationAddress(address)
                .locationName(name)
                .latitude(latitude)
                .longitude(longitude)
                .build()

        when:
        def result = locationRepository.save(location)

        then:
        result.getLocationAddress() == address
        result.getLocationName() == name
        result.getLatitude() == latitude
        result.getLongitude() == longitude
    }

    def "LocationRepository saveAll"() {
        given:
        String address = "인천 광역시 미추홀구 학익1동"
        String name = "명성 약국"
        double latitude = 37.4429272188
        double longitude = 126.6632363094

        def location = Location.builder()
                .locationAddress(address)
                .locationName(name)
                .latitude(latitude)
                .longitude(longitude)
                .build()

        when:
        locationRepository.saveAll(Arrays.asList(location))
        def result = locationRepository.findAll()

        then:
        result.size() == 1
    }

}
