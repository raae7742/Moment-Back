package com.moment.CapturedMomentServer;

import com.moment.CapturedMomentServer.repository.UserRepository;
import com.moment.CapturedMomentServer.service.ImageUploadService;
import com.moment.CapturedMomentServer.service.UserService;
import com.moment.CapturedMomentServer.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

/*김예진 2021-08-08*/
@Configuration
@RequiredArgsConstructor
public class AppConfig{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ImageUploadService imageUploadService;

    @Bean
    public UserService signUpService(){
        return new UserServiceImpl(userRepository, passwordEncoder, imageUploadService);
    }

}
