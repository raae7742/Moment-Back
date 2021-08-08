package com.moment.CapturedMomentServer.service;

import com.moment.CapturedMomentServer.controller.SignUpController;
import com.moment.CapturedMomentServer.domain.User;
import com.moment.CapturedMomentServer.domain.UserRequestDto;
import com.moment.CapturedMomentServer.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional //->없으면 DB에 쌓인다(반복불가)
public class UserServiceTest {

    @Autowired SignUpService signUpService;
    @Autowired SignUpController controller;


    @Test
    void 회원가입() { //한글로 해도 된다!
        //given 상황이 주어짐
        UserRequestDto userDto = new UserRequestDto("name", "name", "aaa@aaa.aaa", "safas", "", "");


        //when 이걸로 실행함
        controller.create(userDto);

        //then 이런 결과가 나옴
        User findUser = signUpService.findOne("aaa@aaa.aaa").get();
        assertThat("aaa@aaa.aaa").isEqualTo(findUser.getEmail());
    }

}
