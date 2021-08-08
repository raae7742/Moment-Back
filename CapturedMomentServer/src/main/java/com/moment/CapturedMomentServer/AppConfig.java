package com.moment.CapturedMomentServer;

import com.moment.CapturedMomentServer.repository.UserRepository;
import com.moment.CapturedMomentServer.repository.UserRepositoryImpl;
import com.moment.CapturedMomentServer.service.SignUpService;
import com.moment.CapturedMomentServer.service.SignUpServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*김예진 2021-08-08*/
@Configuration
public class AppConfig {

    @Bean
    public SignUpService signUpService(){
        System.out.println("call AppConfig.memberService");
        return new SignUpServiceImpl(userRepository());
    }

    @Bean
    public UserRepositoryImpl userRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new UserRepositoryImpl();
    }

}
