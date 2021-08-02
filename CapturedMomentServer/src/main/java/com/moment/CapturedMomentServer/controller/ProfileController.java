package com.moment.CapturedMomentServer.controller;

import com.moment.CapturedMomentServer.domain.ProfileDto;
import com.moment.CapturedMomentServer.domain.User;
import com.moment.CapturedMomentServer.domain.UserRequestDto;
import com.moment.CapturedMomentServer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/* 마이페이지 프로필(Profile) 관련 컨트롤러 (현애, 2021-08-01) */

@RestController
@RequiredArgsConstructor
public class ProfileController {

    private final UserRepository userRepository;

    /* 전달받은 id로 사용자를 검색하고 프로필 정보를 전달하는 API (get) */
    @GetMapping("/my/profile/{id}")
    public ProfileDto readProfile(@PathVariable Long id) {              // request 값은 임시로 id를 받도록 함 (jwt로 수정할 예정)
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );

       ProfileDto profile = new ProfileDto(user);
       return profile;
    }

    /* 테스트를 위해 만든 임시 회원가입 API */
    @PostMapping("/user/signup")
    public User createUser(@RequestBody UserRequestDto requestDto) {
        User user = new User(requestDto);
        return userRepository.save(user);
    }
}
