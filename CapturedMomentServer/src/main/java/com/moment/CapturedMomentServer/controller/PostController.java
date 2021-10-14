package com.moment.CapturedMomentServer.controller;

import com.moment.CapturedMomentServer.config.security.JwtTokenProvider;
import com.moment.CapturedMomentServer.domain.*;
import com.moment.CapturedMomentServer.repository.PostRepository;
import com.moment.CapturedMomentServer.repository.PostTagRepository;
import com.moment.CapturedMomentServer.repository.SpotRepository;
import com.moment.CapturedMomentServer.response.JSONResponse;
import io.swagger.models.auth.In;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public JSONResponse<List<PostResponse>> createPosts(@RequestHeader("X-AUTH-TOKEN") String token){

        List<Post> post = postRepository.findByWriter(jwtTokenProvider.getUserEmail(token));
        List<PostResponse> postRes = new ArrayList<PostResponse>();
        for (int i=0; i< post.size(); i++) {
            postRes.add(new PostResponse(post.get(i),
                    postTagRepository.findByPostId(post.get(i).getId()),
                    spotRepository.findById(post.get(i).getSpotId())));
        }
        JSONResponse<List<PostResponse>> response = new JSONResponse<>();
        response.setStatusCode(200);
        response.setMessage("마이페이지 글 불러오기 성공");
        response.setData(postRes);
        return response;

    }

    @GetMapping("/post/timeline")
    public JSONResponse<List<PostResponse>> getTimelinePost(@RequestParam double lat, @RequestParam double lon) {

        Long spotID = spotRepository.spotIdGet(lat, lon);
        System.out.println("spotId:" + spotID);
        List<Post> post = postRepository.findBySpotId(spotID);

        List<PostResponse> postRes = new ArrayList<PostResponse>();

        for (int i=0; i< post.size(); i++) {
            postRes.add(new PostResponse(post.get(i),
                    postTagRepository.findByPostId(post.get(i).getId()),
                    spotRepository.findById(spotID)));
        }
        JSONResponse<List<PostResponse>> response = new JSONResponse<>();
        response.setStatusCode(200);
        response.setMessage("타임라인 글 불러오기 성공");
        response.setData(postRes);
        return response;
    }
}

@Getter
@NoArgsConstructor
class PostResponse {

    private Long id;
    private String img_url;
    private String contents;
    private Optional<Spot> spot;
    private List<String> tag;

    public PostResponse(Post post, List<PostTag> tag, Optional<Spot> spot){
        this.id = post.getId();
        this.img_url = post.getImg_url();
        this.contents = post.getContents();

        ArrayList<String> tagList = new ArrayList();
        for (int i = 0; i < tag.size(); i++) {

            switch (tag.get(i).getTag()) {
                case 0: tagList.add("풍경"); break;
                case 1: tagList.add("일상"); break;
                case 2: tagList.add("건축"); break;
                case 3: tagList.add("인물"); break;
                case 4: tagList.add("자연"); break;
                case 5: tagList.add("바다"); break;
                case 6: tagList.add("스냅샷"); break;
                case 7: tagList.add("기타"); break;
                default: System.out.println("다른수");

            }
        }

        this.tag = tagList;
        this.spot = spot;
    }


}