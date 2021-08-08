package com.moment.CapturedMomentServer.service;

import com.moment.CapturedMomentServer.domain.User;
import com.moment.CapturedMomentServer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/*2021-08-08 김예진*/
@Service
public interface SignUpService {

    //회원가입
    public String signUp(User user);


    //전체 회원 조회
    public List<User> findUsers();

    //email로 사람찾기
    public Optional<User> findOne(String email);

}
