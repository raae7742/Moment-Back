package com.moment.CapturedMomentServer.domain;

import com.moment.CapturedMomentServer.util.RandomGenerator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Spot {

    @Id
    @GeneratedValue(generator = RandomGenerator.generatorName)
    @GenericGenerator(name = RandomGenerator.generatorName, strategy = "com.moment.CapturedMomentServer.util.RandomGenerator")
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
