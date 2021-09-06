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

        Spot s = spotRepository.findByLatitudeAndLongitude(requestDto.getLatitude(), requestDto.getLongitude());
        if (s != null){
            Post post = new Post(requestDto, s.getId(), jwtTokenProvider.getUserEmail(token));
            postRepository.save(post);
            for(int i=0; i<requestDto.getTag().size(); i++){
                PostTag tag = new PostTag(post.getId(), requestDto.getTag().get(i));
                postTagRepository.save(tag);
            }

        }
        else {
            Spot spot = new Spot(requestDto.getLatitude(), requestDto.getLongitude());
            Post post = new Post(requestDto, spotRepository.save(spot).getId(), jwtTokenProvider.getUserEmail(token));
            for(int i=0; i<requestDto.getTag().size(); i++){
                PostTag tag = new PostTag(post.getId(), requestDto.getTag().get(i));
                postTagRepository.save(tag);
            }
        }

//        if (spotRepository.existsByLatitudeAndLongitude(requestDto.getLatitude(), requestDto.getLongitude())){
//            // 이미 테이블에 그 위도, 경도가 있으면 -> 아이디 찾아오기..
//            Post post = new Post(requestDto, spotRepository.spotIdGet(requestDto.getLatitude(), requestDto.getLongitude()), jwtTokenProvider.getUserEmail(token));
//            Post responsePost = postRepository.save(post);
//            for(int i=0; i<requestDto.getTag().size(); i++){
//                PostTag tag = new PostTag(post.getId(), requestDto.getTag().get(i));
//                postTagRepository.save(tag);
//            }
//
//        }
//        else {
//            // 없으면
//            Spot spot = new Spot(requestDto.getLatitude(), requestDto.getLongitude());
//            Post post = new Post(requestDto, spotRepository.save(spot).getId(), jwtTokenProvider.getUserEmail(token));
//            Post responsePost = postRepository.save(post);
//            for(int i=0; i<requestDto.getTag().size(); i++){
//                PostTag tag = new PostTag(post.getId(), requestDto.getTag().indexOf(i) + 2);
//                postTagRepository.save(tag);
//            }
//
//
//        }
        JSONResponse<Post> response = new JSONResponse<>();
        response.setStatusCode(200);
        response.setMessage("글 작성 성공");
        response.setData(null);
        return response;

    }

}
