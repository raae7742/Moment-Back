package com.moment.CapturedMomentServer.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PostRequestDto {
    private String img_url;
    private String contents;
    private final double latitude;
    private final double longitude;
}
