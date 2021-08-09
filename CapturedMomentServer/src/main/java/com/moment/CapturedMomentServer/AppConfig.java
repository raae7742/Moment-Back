package com.moment.CapturedMomentServer;

import com.moment.CapturedMomentServer.repository.UserRepository;
import com.moment.CapturedMomentServer.service.SignUpService;
import com.moment.CapturedMomentServer.service.SignUpServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*김예진 2021-08-08*/
@Configuration
@RequiredArgsConstructor
public class AppConfig {

    private final UserRepository userRepository;

    @Bean
    public SignUpService signUpService(){
        System.out.println("call AppConfig.memberService");
        return new SignUpServiceImpl(userRepository);
    }


}
