package com.moment.CapturedMomentServer.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.function.LongBinaryOperator;

@Getter
@NoArgsConstructor
@Entity
public class Post extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private int writer;

    @Column(nullable = false)
    private String img_url;

    @Column(nullable = true)
    private String contents;

    @Column(nullable = false)
    private Long spot_id;

    public Post(PostRequestDto requestDto, Long spotId){
        this.writer = 190; // 이후 수정
        this.img_url = requestDto.getImg_url();
        this.contents = requestDto.getContents();
        this.spot_id = spotId;
    }
}
