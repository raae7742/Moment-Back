package com.moment.CapturedMomentServer.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    // 스웨거 페이지에 소개될 설명들
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Demo")
                .description("API EXAMPLE")
                .build();
    }

    @Bean
    public Docket commonApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .consumes(getConsumeContentTypes())     // Request Content-Type
                .produces(getProduceContentTypes())     // Response Content-Type
                .apiInfo(this.apiInfo())                // Swaggger API 문서 설명
                .select()                               // apis, paths를 사용해주기 위한 builder
                .apis(RequestHandlerSelectors.basePackage("com.moment.CapturedMomentServer.controller"))    // 탐색할 패키지 필터링
                .paths(PathSelectors.any())             // 보여줄 api 필터링
                .build();
    }

    private Set<String> getConsumeContentTypes() {
        Set<String> consumes = new HashSet<>();
        consumes.add("application/json;charset=UTF-8");

        return consumes;
    }

    private Set<String> getProduceContentTypes() {
        Set<String> produces = new HashSet<>();
        produces.add("application/json;charset=UTF-8");

        return produces;
    }
}
