package com.moment.CapturedMomentServer.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Spot {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private double longitude;

    // 생성자
    public Spot(double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Spot(SpotRequestDto requestDto){
        this.latitude = requestDto.getLatitude();
        this.longitude = requestDto.getLongitude();
    }


}
