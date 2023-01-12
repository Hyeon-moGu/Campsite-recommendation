package com.example.project.location.entitiy;

import com.example.project.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "location")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Location extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String locationName;
    private String locationAddress;
    private double latitude;
    private double longitude;

    public void changeLocationAddress(String address){
        this.locationAddress = address;
    }
}
