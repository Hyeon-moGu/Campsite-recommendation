package com.example.project.location.service

import com.example.project.AbstractIntegrationContainerBaseTest
import com.example.project.location.entitiy.Location
import com.example.project.location.repository.LocationRepository
import org.springframework.beans.factory.annotation.Autowired

class LocationRepositoryServiceTest extends AbstractIntegrationContainerBaseTest{

    @Autowired
    private LocationRepositoryService locationRepositoryService;

    @Autowired
    LocationRepository locationRepository;

    def setup() {
        locationRepository.deleteAll();
    }

    def "LocationRepository update - dirty checking success"() {
        given:
        String inputAddress = "인천 광역시 미추홀구 소성로 136"
        String modifyAddress = "인천 광역시 서구 신진말로 37-1"
        String name = "명성 약국"

        def location = Location.builder()
                .locationAddress(inputAddress)
                .locationName(name)
                .build()

        when:
        def entity = locationRepository.save(location)
        locationRepositoryService.updateAddress(entity.getId(), modifyAddress)

        def result = locationRepository.findAll()

        then:
        result.get(0).getLocationAddress() == modifyAddress
    }

    def "LocationRepository update - dirty checking fail"() {
        given:
        String inputAddress = "인천 광역시 미추홀구 소성로 136"
        String modifyAddress = "인천 광역시 서구 신진말로 37-1"
        String name = "명성 약국"

        def location = Location.builder()
                .locationAddress(inputAddress)
                .locationName(name)
                .build()

        when:
        def entity = locationRepository.save(location)
        locationRepositoryService.updateAddressWithoutTransactional(entity.getId(), modifyAddress)

        def result = locationRepository.findAll()

        then:
        result.get(0).getLocationAddress() == inputAddress
    }

    def "self invocation"() {

        given:
        String address = "인천 광역시 미추홀구 소성로 136"
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
        locationRepositoryService.bar(Arrays.asList(location))

        then:
        def e = thrown(RuntimeException.class)
        def result = locationRepositoryService.findAll()
        result.size() == 1 // 트랜잭션이 적용되지 않는다( 롤백 적용 X )
    }

}
