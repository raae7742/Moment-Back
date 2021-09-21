package com.moment.CapturedMomentServer.service;

import com.moment.CapturedMomentServer.domain.User;
import com.moment.CapturedMomentServer.domain.UserRequestDto;
import com.moment.CapturedMomentServer.repository.UserRepository;
import com.moment.CapturedMomentServer.util.ConflictError;
import com.moment.CapturedMomentServer.util.UnauthorizedError;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/* 2021-08-08 김예진, 08-15 장현애 */

@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;                              // 패스워드 인코더
    private final ImageUploadService imageUploadService;

    public User signUp(UserRequestDto requestDto, MultipartFile image){
        if (validateDuplicateMember(requestDto.getEmail()))
            throw new ConflictError("이미 가입된 e-mail입니다.");        // 이메일 중복 체크
        requestDto.setPw(passwordEncoder.encode(requestDto.getPw()));           // 비밀번호 암호화
        requestDto.setImg_url(imageUploadService.restore(image));
        return userRepository.save(new User(requestDto));
    }

    public User signIn(UserRequestDto.LoginDto loginDto) {

        User user = userRepository.findByEmail(loginDto.getEmail())             // 이메일로 user 검색
                .orElseThrow(() -> new UnauthorizedError("가입되지 않은 e-mail입니다."));

        if (!passwordEncoder.matches(loginDto.getPw(), user.getPw()))           // 패스워드 확인
            throw new UnauthorizedError("잘못된 비밀번호입니다.");

        return user;
    }

    //email 유효성 확인
    private Boolean validateDuplicateMember(String email){
        return userRepository.existsByEmail(email);
    }

    //전체 회원 조회
    public List<User> findUsers(){
        return userRepository.findAll();
    }

    //email로 사람찾기
    public Optional<User> findOne(String email){
        return userRepository.findByEmail(email);
    }

    @Override
    public String updateProfile(String email, UserRequestDto.ProfileDto requestDto) {
        User user = userRepository.findByEmail(email).orElseThrow(                              // 이메일로 유저 검색
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        user.update(requestDto);

        return email;
    }

}
