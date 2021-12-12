package com.moment.CapturedMomentServer.domain;

import com.moment.CapturedMomentServer.util.RandomGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/* 2021-11-08 김예진*/
@Getter
@Entity
@Table(name = "code")
@AllArgsConstructor
@NoArgsConstructor
public class VerifyCode extends Timestamped{

    @Id
    @Column(name = "code")
    @GeneratedValue(generator = RandomGenerator.generatorName)
    @GenericGenerator(name = RandomGenerator.generatorName, strategy = "com.moment.CapturedMomentServer.util.RandomGenerator")
    String code;

    @Column(name = "email", unique = true)
    String email;

    public VerifyCode(VerifyCodeDto dto){
        this.email = dto.getEmail();
    }
}
