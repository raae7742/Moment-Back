package com.moment.CapturedMomentServer.controller;

/* /spots 김혜수 2021-08-02*/

import antlr.PreservingFileWriter;
import com.moment.CapturedMomentServer.domain.Spot;
import com.moment.CapturedMomentServer.repository.SpotRepository;
import com.moment.CapturedMomentServer.response.JSONResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SpotController {

    private final SpotRepository spotRepository;

    @ApiOperation(value = "지도 마커정보 가져오기",
            httpMethod = "GET",
            response = JSONResponse.class,
            notes = "화면 중앙의 위도, 경도를 통해 지도 위에 띄울 마커 정보 리스트를 조회한다."
    )
    @ApiImplicitParams( value = {
            @ApiImplicitParam(name = "latitude", value = "위도", required = true, dataType = "double", paramType = "query"),
            @ApiImplicitParam(name = "longitude", value = "경도", required = true, dataType = "double", paramType = "query")})
    @GetMapping("/spots")
    public JSONResponse<List<Spot>> getSpots(@RequestParam double latitude, @RequestParam double longitude){
        JSONResponse<List<Spot>> response = new JSONResponse<>();
        response.setStatusCode(200);
        response.setMessage("spot 조회 성공");
        response.setData(spotRepository.datas(latitude, longitude));

        return response;
    }

}
