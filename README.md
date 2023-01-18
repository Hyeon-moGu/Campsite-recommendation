# Campsite-recommendation
[공공기관 데이터(야영장 등록 정보)](https://www.data.go.kr/data/15037499/fileData.do)와 [외부 API(카카오 주소 검색 API)](https://developers.kakao.com/docs/latest/ko/local/dev-guide)를 활용하여 일정 반경 내의 캠핑장 목록을 보여준다.<br>

공공기관 데이터는 필요한 부분만 가공하여 사용하였고, 캠핑장의 목록은 [카카오 지도URL](https://apis.map.kakao.com/web/guide/#routeurl)로 제공된다.

## 요구사항 분석

* 캠핑장 현황 데이터를 관리하고 있고 현황 데이터는 위도,경도의 위치 정보를 가지고 있다 라고 가정한다.
* 해당 서비스로 주소 정보를 입력하여 요청하면 입력 위치 기준에서 거리순으로 가까운 캠핑장 5개를 추출 한다.
* 주소는 도로명 주소 또는 지번을 입력하여 요청 받는다. ([카카오 우편번호 서비스](https://postcode.map.daum.net/guide) 사용)
* 입력한 위치의 위도,경도와 캠핑장의 위도,경도 사이의 거리를 [haversine formula(하버사인 포뮬러)](https://en.wikipedia.org/wiki/Haversine_formula)로 계산한다.
* 입력한 주소 정보에서 반경(50km)내에 있는 캠핑장만 보여준다.
* 추출된 데이터는 길안내URL 및 로드뷰URL로 제공한다.
* 길안내 URL은 가독성을 위해 shorten url로 제공하며 사용되는 key값은 [base62](https://github.com/seruco/base62)를 통해 인코딩하여 제공한다.

## 기술 스택
`JDK11` `Spring Boot 2.6.7` `Spring Data JPA` <br><br>
`Gradle` `Handlebars` `Lombok` `Github` `Docker` <br><br>
`AWS EC2` `Redis` `MariaDB` `Spock` `Testcontainers`


## 결과물
[페이지 이동](http://3.36.41.243/)

## Process

![Campsite_Recommendation_Process](https://user-images.githubusercontent.com/100026743/213068943-a62ab848-f896-41a0-a10b-0052c7660a85.JPG)


## Reference
Data    - [공공기관 데이터](https://www.data.go.kr/data/15037499/fileData.do) <br>
API     - [카카오 주소검색](https://developers.kakao.com/docs/latest/ko/local/dev-guide) <br>
API     - [카카오 우편번호](https://postcode.map.daum.net/guide) <br>
API     - [카카오 지도URL](https://apis.map.kakao.com/web/guide/#routeurl) <br>
Formula - [Haversine_formula](https://en.wikipedia.org/wiki/Haversine_formula)
