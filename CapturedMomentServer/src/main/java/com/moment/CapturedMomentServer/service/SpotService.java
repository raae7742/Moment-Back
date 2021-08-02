package com.moment.CapturedMomentServer.service;

import com.moment.CapturedMomentServer.repository.SpotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpotService {

    private final SpotRepository spotRepository;


}
