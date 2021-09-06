package com.moment.CapturedMomentServer.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PostTagRequestDto {

    private Long post_id;
    private Integer tag;
}
