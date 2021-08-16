package com.moment.CapturedMomentServer;

import com.moment.CapturedMomentServer.repository.UserRepository;
import com.moment.CapturedMomentServer.service.UserService;
import com.moment.CapturedMomentServer.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

/*김예진 2021-08-08*/
@Configuration
@RequiredArgsConstructor
public class AppConfig {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public UserService signUpService(){
        return new UserServiceImpl(userRepository, passwordEncoder);
    }


}
