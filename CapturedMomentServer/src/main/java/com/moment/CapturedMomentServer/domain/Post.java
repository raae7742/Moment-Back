package com.moment.CapturedMomentServer.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Post extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    private String img_url;

    @Column(nullable = true)
    private String contents;

    @Column(nullable = false)
    @JoinColumn(name = "spot_id")
    private Long spotId;

    public Post(PostRequestDto requestDto, Long spotId, String userEmail){
        this.writer = userEmail;
        this.img_url = requestDto.getImg_url();
        this.contents = requestDto.getContents();
        this.spotId = spotId;
    }
}
