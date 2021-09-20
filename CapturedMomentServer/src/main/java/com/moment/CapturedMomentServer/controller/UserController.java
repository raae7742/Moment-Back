package com.moment.CapturedMomentServer.controller;


import org.springframework.http.MediaType;
import com.moment.CapturedMomentServer.config.security.JwtTokenProvider;
import com.moment.CapturedMomentServer.domain.User;
import com.moment.CapturedMomentServer.domain.UserRequestDto;
import com.moment.CapturedMomentServer.repository.UserRepository;
import com.moment.CapturedMomentServer.response.JSONResponse;
import com.moment.CapturedMomentServer.service.UserService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;

/* 김예진 2021-08-08, 장현애 08-15 */

@Controller
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @ApiOperation(value = "회원가입",
            httpMethod = "POST",
            response = JSONResponse.class,
            notes = "회원 정보를 받아와 회원을 등록한다."
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "회원가입 성공")
    })
    @PostMapping(value = "/user/signup",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public JSONResponse<User> create(@RequestPart(value = "user") UserRequestDto userDto, @RequestPart(value = "image", required = false) MultipartFile image){

        userDto.setRoles(Collections.singletonList("ROLE_USER"));// 최초 가입시 USER로 설정
        User user = userService.signUp(userDto, image);                  // 서비스로 user 정보 전달

        JSONResponse<User> response = new JSONResponse<>();
        if(user != null) {
            response.setStatusCode(200);
            response.setMessage("회원가입 성공");
            response.setData(user);
        }

        return response;
    }

    @ApiOperation(value = "로그인",
            httpMethod = "POST",
            response = JSONResponse.class,
            notes = "회원의 이메일과 패스워드로 로그인을 시도한 후, 성공 시 JWT 토큰을 반환한다."
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "로그인 성공"),
            @ApiResponse(code = 401, message = "이메일 또는 비밀번호 오류")
    })
    @PostMapping("/user/signin")
    public JSONResponse<String> login(@RequestBody UserRequestDto.LoginDto requestDto) {
        User user = userService.signIn(requestDto);

        JSONResponse<String> response = new JSONResponse<>();
        if (user != null){
            response.setStatusCode(200);
            response.setMessage("로그인 성공");
            response.setData(jwtTokenProvider.createToken(user.getEmail(), user.getRoles()));
        }

        return response;
    }

    @ApiOperation(value = "마이페이지 프로필 정보 조회",
            httpMethod = "GET",
            response = JSONResponse.class,
            notes = "헤더의 JWT Token으로 사용자를 검색하고 프로필 정보를 조회한다."
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "프로필 조회 성공")
    })
    @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "JWT 토큰", required = true, dataType = "String", paramType = "header")
    @GetMapping("/user/mypage")
    public JSONResponse readProfile(@RequestHeader("X-AUTH-TOKEN") String token) { // "X-AUTH-TOKEN" 헤더에서 토큰 받아오기
        String email = jwtTokenProvider.getUserEmail(token);                                    // 토큰에서 유저 이메일 추출

        User user = userRepository.findByEmail(email).orElseThrow(                              // 이메일로 유저 검색
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        UserRequestDto.ProfileDto profile = new UserRequestDto.ProfileDto(user);                // 프로필 정보 전달

        JSONResponse<UserRequestDto.ProfileDto> response = new JSONResponse<>();
        response.setStatusCode(200);
        response.setMessage("프로필 조회 성공");
        response.setData(profile);
        return response;
    }

    @ApiOperation(value = "마이페이지 프로필 정보 수정",
            httpMethod = "PUT",
            response = JSONResponse.class,
            notes = "JWT Token에 저장된 유저의 프로필 정보를 수정한다."
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "프로필 수정 성공")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "JWT 토큰", required = true, dataType = "String", paramType = "header"),
    })
    @PutMapping("/user/mypage")
    public JSONResponse updateProfile(@RequestHeader("X-AUTH-TOKEN") String token, @RequestBody UserRequestDto.ProfileDto requestDto) {
        JSONResponse<String> response = new JSONResponse<>();
        response.setStatusCode(200);
        response.setMessage("프로필 업데이트 성공");
        response.setData(userService.updateProfile(jwtTokenProvider.getUserEmail(token), requestDto));

        return response;
    }
}
