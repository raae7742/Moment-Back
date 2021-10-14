package com.moment.CapturedMomentServer.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class PostTag {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Long post_id;

    @Column(nullable = false)
    private int tag;

    public PostTag(Long postId, Integer tag){
        this.post_id = postId;
        this.tag = tag;
    }
}