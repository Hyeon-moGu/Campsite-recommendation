package com.example.project.direction.service

import com.example.project.api.dto.DocumentDto
import com.example.project.location.dto.LocationDto
import com.example.project.location.service.LocationSearchService
import spock.lang.Specification

class DirectionServiceTest extends Specification {

    private LocationSearchService locationSearchService = Mock()

    private DirectionService directionService = new DirectionService(locationSearchService)

    private List<LocationDto> locationList

    def setup(){
        locationList = new ArrayList<>()
        locationList.addAll(
                LocationDto.builder()
                        .id(1L)
                        .locationName("서창캠핑장")
                        .locationAddress("인천 광역시 남동구 운연로 31")
                        .latitude(37.4386179)
                        .longitude(126.7551527)
                        .build(),
                LocationDto.builder()
                        .id(2L)
                        .locationName("에제르파크")
                        .locationAddress("인천 광역시 남동구 만의골로 155")
                        .latitude(37.4570283)
                        .longitude(126.7719438)
                        .build()
        )
    }

    def "buildDirectionList - 결과 값이 거리 순으로 정렬 확인"() {
        given:
        def addressName = "인천 광역시 남동구 구월동 "
        double inputLatitude = 37.4558567
        double inputLongitude = 126.7045896

        def documentDto = DocumentDto.builder()
                .addressName(addressName)
                .latitude(inputLatitude)
                .longitude(inputLongitude)
                .build()

        when:
        locationSearchService.searchLocationDtoList() >> locationList

        def result = directionService.buildDirectionList(documentDto)

        then:
        result.size() == 2
        result.get(0).targetLocationName == "서창캠핑장"
        result.get(1).targetLocationName == "에제르파크"

    }

    def "buildDirectionList - 반경 50km 내에 검색이 되는지 확인"(){
        given:
        locationList.add(
                LocationDto.builder()                               // 50km 밖의 데이터
                        .id(3L)
                        .locationName("소풍정원 캠핑장")
                        .locationAddress("경기도 평택시 고덕면 궁리")
                        .latitude(37.0167678)
                        .longitude(127.0206434)
                        .build())

        def addressName = "인천 광역시 남동구 구월동 "
        double inputLatitude = 37.4558567
        double inputLongitude = 126.7045896

        def documentDto = DocumentDto.builder()
                .addressName(addressName)
                .latitude(inputLatitude)
                .longitude(inputLongitude)
                .build()

        when:
        locationSearchService.searchLocationDtoList() >> locationList

        def result = directionService.buildDirectionList(documentDto)

        then:
        result.size() == 2
        result.get(0).targetLocationName == "서창캠핑장"
        result.get(1).targetLocationName == "에제르파크"

    }
}
