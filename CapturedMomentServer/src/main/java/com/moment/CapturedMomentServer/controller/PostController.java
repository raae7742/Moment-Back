package com.moment.CapturedMomentServer.controller;

import com.moment.CapturedMomentServer.config.security.JwtTokenProvider;
import com.moment.CapturedMomentServer.domain.*;
import com.moment.CapturedMomentServer.repository.PostRepository;
import com.moment.CapturedMomentServer.repository.PostTagRepository;
import com.moment.CapturedMomentServer.repository.SpotRepository;
import com.moment.CapturedMomentServer.response.JSONResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

//        Spot s = spotRepository.findByLatitudeAndLongitude(requestDto.getLatitude(), requestDto.getLongitude());
//        if (s != null){
//            // 이미 테이블에 위도, 경도 있는 경우
//            Post post = new Post(requestDto, s.getId(), jwtTokenProvider.getUserEmail(token));
//            postRepository.save(post);
//            for(int i=0; i<requestDto.getTag().size(); i++){
//                PostTag tag = new PostTag(post.getId(), requestDto.getTag().get(i));
//                postTagRepository.save(tag);
//            }
//        }
//        else {
//            // 테이블에 위도, 경도 없는 경우
//            Spot spot = new Spot(requestDto.getLatitude(), requestDto.getLongitude());
//            Post post = new Post(requestDto, spotRepository.save(spot).getId(), jwtTokenProvider.getUserEmail(token));
//            for(int i=0; i<requestDto.getTag().size(); i++){
//                PostTag tag = new PostTag(post.getId(), requestDto.getTag().get(i));
//                postTagRepository.save(tag);
//            }
//        }

        if (spotRepository.existsByLatitudeAndLongitude(requestDto.getLatitude(), requestDto.getLongitude())){
            // 이미 테이블에 그 위도, 경도가 있으면 -> 아이디 찾아오기..
            Post post = new Post(requestDto, spotRepository.spotIdGet(requestDto.getLatitude(), requestDto.getLongitude()), jwtTokenProvider.getUserEmail(token));
            Post responsePost = postRepository.save(post);
            for(int i=0; i<requestDto.getTag().size(); i++){
                PostTag tag = new PostTag(post.getId(), requestDto.getTag().get(i));
                postTagRepository.save(tag);
            }

        }
        else {
            // 없으면
            Spot spot = new Spot(requestDto.getLatitude(), requestDto.getLongitude());
            Post post = new Post(requestDto, spotRepository.save(spot).getId(), jwtTokenProvider.getUserEmail(token));
            Post responsePost = postRepository.save(post);
            for(int i=0; i<requestDto.getTag().size(); i++){
                PostTag tag = new PostTag(post.getId(), requestDto.getTag().indexOf(i) + 2);
                postTagRepository.save(tag);
            }


        }
        JSONResponse<Post> response = new JSONResponse<>();
        response.setStatusCode(200);
        response.setMessage("글 작성 성공");
        response.setData(null);
        return response;

    }

    @GetMapping("/post/mypage")
    public JSONResponse<List<Post>> createPosts(@RequestHeader("X-AUTH-TOKEN") String token){

        //Post post = postRepository.findByWriter(jwtTokenProvider.getUserEmail(token));

        JSONResponse<List<Post>> response = new JSONResponse<>();
        response.setStatusCode(200);
        response.setMessage("글 불러오기 성공");
        response.setData(postRepository.findByWriter(jwtTokenProvider.getUserEmail(token)));

        return response;
    }

    @GetMapping("/post/timeline")
    public JSONResponse<List<Post>> getTimelinePost(@RequestParam double lat, @RequestParam double lon) {

        Long spotID = spotRepository.findByLatitudeAndLongitude(lat, lon).getId();

        JSONResponse<List<Post>> response = new JSONResponse<>();
        response.setStatusCode(200);
        response.setMessage("타임라인 글 불러오기 성공");
        response.setData(postRepository.findBySpotId(spotID));
        return response;
    }


}

@Getter
@NoArgsConstructor
class PostMine {

    private Long id;
    private String img_url;
    private String contents;
    private Spot spot;
    private List<String> tag;

    public PostMine(Post post, PostTag tag, Spot spot){
        this.id = post.getId();
        this.img_url = post.getImg_url();
        this.contents = post.getContents();
        this.spot = spot;
    }

}