package com.example.project.direction.entity;

import com.example.project.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "direction")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Direction extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 사용자
    private String inputAddress;
    private double inputLatitude;
    private double inputLongitude;

    // 목적지
    private String targetLocationName;
    private String targetAddress;
    private double targetLatitude;
    private double targetLongitude;

    // 사용자와 목적지 사이의 거리
    private double distance;
}
