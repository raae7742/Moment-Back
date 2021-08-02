package com.moment.CapturedMomentServer.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/* 김혜수 2021-08-02*/

@Getter
@RequiredArgsConstructor
public class SpotRequestDto {

    private final double latitude;
    private final double longitude;
}
