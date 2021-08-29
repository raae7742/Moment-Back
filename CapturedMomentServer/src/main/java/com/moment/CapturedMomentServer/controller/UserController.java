package com.moment.CapturedMomentServer.controller;


import com.moment.CapturedMomentServer.config.security.JwtTokenProvider;
import com.moment.CapturedMomentServer.domain.User;
import com.moment.CapturedMomentServer.domain.UserRequestDto;
import com.moment.CapturedMomentServer.repository.UserRepository;
import com.moment.CapturedMomentServer.response.JSONResponse;
import com.moment.CapturedMomentServer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

/* 김예진 2021-08-08, 장현애 08-15 */

@Controller
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/user/signup")
    public JSONResponse<User> create(@RequestBody UserRequestDto userDto){
        userDto.setRoles(Collections.singletonList("ROLE_USER")); // 최초 가입시 USER로 설정
        User user = userService.signUp(userDto);                // 서비스로 user 정보 전달

        JSONResponse<User> response = new JSONResponse<>();
        if(user != null) {
            response.setStatusCode(200);
            response.setMessage("회원가입 성공");
            response.setData(user);
        }

        return response;
    }

    // 로그인
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

    /* 전달받은 토큰으로 사용자를 검색하고 프로필 정보를 전달하는 API (get) */
    @GetMapping("/user/mypage")
    public UserRequestDto.ProfileDto readProfile(@RequestHeader("X-AUTH-TOKEN") String token) { // "X-AUTH-TOKEN" 헤더에서 토큰 받아오기
        String email = jwtTokenProvider.getUserEmail(token);                                    // 토큰에서 유저 이메일 추출

        User user = userRepository.findByEmail(email).orElseThrow(                              // 이메일로 유저 검색
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );

        UserRequestDto.ProfileDto profile = new UserRequestDto.ProfileDto(user);                // 프로필 정보 전달
        return profile;
    }
}
