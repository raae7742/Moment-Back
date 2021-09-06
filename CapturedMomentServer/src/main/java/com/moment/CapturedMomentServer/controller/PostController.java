package com.moment.CapturedMomentServer.controller;

import com.moment.CapturedMomentServer.config.security.JwtTokenProvider;
import com.moment.CapturedMomentServer.domain.*;
import com.moment.CapturedMomentServer.repository.PostRepository;
import com.moment.CapturedMomentServer.repository.PostTagRepository;
import com.moment.CapturedMomentServer.repository.SpotRepository;
import com.moment.CapturedMomentServer.response.JSONResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostRepository postRepository;
    private final SpotRepository spotRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PostTagRepository postTagRepository;

    @PostMapping("/post")
    public JSONResponse<Post> createPosts(@RequestHeader("X-AUTH-TOKEN") String token,
                                          @RequestBody PostRequestDto requestDto) {

        Spot spot = new Spot(requestDto.getLatitude(), requestDto.getLongitude());
//        Long spotId = spot.getId();
//        Spot responseSpot = spotRepository.save(spot);
        Post post = new Post(requestDto, spotRepository.save(spot).getId(), jwtTokenProvider.getUserEmail(token));
        Post responsePost = postRepository.save(post);

        for(int i=0; i<requestDto.getTag().size(); i++){
            PostTag tag = new PostTag(post.getId(), requestDto.getTag().indexOf(i) + 2);
            postTagRepository.save(tag);
        }

        JSONResponse<Post> response = new JSONResponse<>();
        response.setStatusCode(200);
        response.setMessage("글 작성 성공");
        response.setData(responsePost);
        return response;
    }

}
