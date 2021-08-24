package com.moment.CapturedMomentServer.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.moment.CapturedMomentServer.util.RandomGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "RandomTest")
@Builder
@AllArgsConstructor
public class RandomIDTest {  // SpringSecurity 사용을 위해 UserDetails 상속

    @JsonIgnore
    @Id
    @GeneratedValue(generator = RandomGenerator.generatorName)
    @GenericGenerator(name = RandomGenerator.generatorName, strategy = "com.moment.CapturedMomentServer.util.RandomGenerator")
    /* 긴 id 생성 (간단함)
    @Column(name = "id", columnDefinition = "CHAR(32)")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")*/
    private String id;                // 회원 id

    @Column(name = "name")
    private String name;       // 회원 실명

    @Column(name = "email", unique = true)
    private String email;

    public RandomIDTest(RandomIdTestDto dto){
        this.name = dto.getName();
        this.email = dto.getEmail();
    }
}
