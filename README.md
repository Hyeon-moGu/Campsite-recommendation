# Campsite-recommendation
[공공기관 데이터(야영장 등록 정보)](https://www.data.go.kr/data/15037499/fileData.do)와 [외부 API(카카오 주소 검색 API)](https://developers.kakao.com/docs/latest/ko/local/dev-guide)를 활용하여 일정 반경 내의 캠핑장 목록을 보여준다.<br>

공공기관 데이터는 필요한 부분만 가공하여 사용하였고, 캠핑장의 목록은 [카카오 지도URL](https://apis.map.kakao.com/web/guide/#routeurl)로 제공된다.

## 요구사항 분석

~~

## 기술 스택
`JDK11` `Spring Boot 2.6.7` `Spring Data JPA` <br><br>
`Gradle` `Handlebars` `Lombok` `Github` `Docker` <br><br>
`AWS EC2` `Redis` `MariaDB` `Spock` `Testcontainers`


## 데모페이지
[페이지 이동](http://3.36.41.243/)

## Process

~~

## Reference
Data    - [공공기관 데이터](https://www.data.go.kr/data/15037499/fileData.do) <br>
API     - [카카오 주소검색](https://developers.kakao.com/docs/latest/ko/local/dev-guide) <br>
API     - [카카오 우편번호](https://postcode.map.daum.net/guide) <br>
API     - [카카오 지도URL](https://apis.map.kakao.com/web/guide/#routeurl) <br>
Formula - [Haversine_formula](https://en.wikipedia.org/wiki/Haversine_formula)
