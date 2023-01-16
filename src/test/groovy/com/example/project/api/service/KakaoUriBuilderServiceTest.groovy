package com.example.project.api.service

import spock.lang.Specification

import java.nio.charset.StandardCharsets

class KakaoUriBuilderServiceTest extends Specification{

    private KakaoUriBuilderService kakaoUriBuilderService;

    def setup(){
        kakaoUriBuilderService = new KakaoUriBuilderService();
    }

    def "buildUriByAddress - 한글 파라미터일 경우 정상적인 인코딩"(){
        given:
        String address = "인천 광역시 남동구"
        def charset = StandardCharsets.UTF_8

        when:
        def uri = kakaoUriBuilderService.buildUriByAddressSearch(address)
        def decodeResult = URLDecoder.decode(uri.toString(), charset)

        then:
        decodeResult == "https://dapi.kakao.com/v2/local/search/address.json?query=인천 광역시 남동구"
    }
}
