package com.moment.CapturedMomentServer.controller;

import com.moment.CapturedMomentServer.domain.Post;
import com.moment.CapturedMomentServer.domain.PostRequestDto;
import com.moment.CapturedMomentServer.domain.Spot;
import com.moment.CapturedMomentServer.repository.PostRepository;
import com.moment.CapturedMomentServer.repository.SpotRepository;
import com.moment.CapturedMomentServer.response.JSONResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostRepository postRepository;
    private final SpotRepository spotRepository;

    @PostMapping("/post")
    public JSONResponse<Post> createPosts(@RequestHeader("X-AUTH-TOKEN") String token,
                                          @RequestBody PostRequestDto requestDto) {

        Spot spot = new Spot(requestDto.getLatitude(), requestDto.getLongitude());
//        Long spotId = spot.getId();
//        Spot responseSpot = spotRepository.save(spot);
        Post post = new Post(requestDto, spotRepository.save(spot).getId());
        Post responsePost = postRepository.save(post);

        JSONResponse<Post> response = new JSONResponse<>();
        response.setStatusCode(200);
        response.setMessage("글 작성 성공");
        response.setData(responsePost);
        return response;
    }

}
