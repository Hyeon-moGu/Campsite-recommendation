package com.example.project.direction.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OutputDto {

    private String locationName;    // 캠핑장 이름
    private String locationAddress; // 캠핑장 주소
    private String directionUrl;    // 길안내 url
    private String roadViewUrl;     // 로드뷰 url
    private String distance;        // 거리
}
