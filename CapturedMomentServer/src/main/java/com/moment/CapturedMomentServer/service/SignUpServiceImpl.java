package com.moment.CapturedMomentServer.service;

import com.moment.CapturedMomentServer.domain.User;
import com.moment.CapturedMomentServer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/*2021-08-08 김예진*/
@Transactional
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService{

    private final UserRepository userRepository;

    public String signUp(User user){
        validateDuplicateMember(user);
        userRepository.save(user);
        return user.getEmail();
    }


    //email 유효성 확인
    private void validateDuplicateMember(User user){
        userRepository.findByEmail(user.getEmail());
    }

    //전체 회원 조회
    public List<User> findUsers(){
        return userRepository.findAll();
    }

    //email로 사람찾기
    public Optional<User> findOne(String email){
        return userRepository.findByEmail(email);
    }

}
