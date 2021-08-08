package com.moment.CapturedMomentServer.controller;


import com.moment.CapturedMomentServer.domain.User;
import com.moment.CapturedMomentServer.domain.UserRequestDto;
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

    /*@GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }*/

    @PostMapping("/user/signup")
    public String create(@RequestBody UserRequestDto userDto){

       /* User user = new User();
        user.update(userDto);
        signUpService.signUp(user);
        System.out.println(user.getName());
        return "redirect:/";*/
        User user = new User(userDto);
        signUpService.signUp(user);
        return null;//"redirect:/";
    }

}
