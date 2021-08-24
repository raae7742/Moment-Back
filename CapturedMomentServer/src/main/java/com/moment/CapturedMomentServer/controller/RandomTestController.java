package com.moment.CapturedMomentServer.controller;

import com.moment.CapturedMomentServer.domain.RandomIDTest;
import com.moment.CapturedMomentServer.domain.RandomIdTestDto;
import com.moment.CapturedMomentServer.repository.IdTestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@Controller
public class RandomTestController {
    private final IdTestRepository repository;

    @PostMapping("test/id")
    public String create(@RequestBody RandomIdTestDto dto){
        RandomIDTest test = repository.save(new RandomIDTest(dto));
        return test.getId();
    }
}
