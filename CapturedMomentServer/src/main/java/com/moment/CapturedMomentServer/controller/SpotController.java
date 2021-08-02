package com.moment.CapturedMomentServer.controller;

/* /spots 김혜수 2021-08-02*/

import antlr.PreservingFileWriter;
import com.moment.CapturedMomentServer.domain.Spot;
import com.moment.CapturedMomentServer.repository.SpotRepository;
import com.moment.CapturedMomentServer.response.JSONResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SpotController {

    private final SpotRepository spotRepository;

    @GetMapping("/spots")
    public JSONResponse<List<Spot>> getSpots(@RequestParam double latitude, @RequestParam double longitude){
        JSONResponse<List<Spot>> response = new JSONResponse<>();
        response.setStatusCode(200);
        response.setMessage("spot 조회 성공");
        response.setData(spotRepository.datas(latitude, longitude));

        return response;
    }

}
