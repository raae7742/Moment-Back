package com.moment.CapturedMomentServer.controller;


import com.moment.CapturedMomentServer.domain.User;
import com.moment.CapturedMomentServer.domain.UserRequestDto;
import com.moment.CapturedMomentServer.repository.UserRepository;
import com.moment.CapturedMomentServer.response.JSONResponse;
import com.moment.CapturedMomentServer.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*김예진 2021-08-08*/
@Controller
@RestController
@RequiredArgsConstructor
public class SignUpController {
    private final SignUpService signUpService;

    @PostMapping("/user/signup")
    public JSONResponse<String> create(@RequestBody UserRequestDto userDto){
        User user = new User(userDto);
        String email = signUpService.signUp(user);
        JSONResponse<String> response = new JSONResponse<>();
        response.setStatusCode(200);
        response.setMessage("회원가입 성공");
        response.setData(email);
        return response;
    }

}
