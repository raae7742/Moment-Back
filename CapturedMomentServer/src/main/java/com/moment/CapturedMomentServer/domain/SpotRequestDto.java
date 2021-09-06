package com.moment.CapturedMomentServer.domain;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/* 김혜수 2021-08-02*/

@Getter
@RequiredArgsConstructor
public class SpotRequestDto {

    @ApiParam(value = "위도", required = true)
    @ApiModelProperty(example = "위도")
    private final double latitude;

    @ApiParam(value = "경도", required = true)
    @ApiModelProperty(example = "경도")
    private final double longitude;
}
