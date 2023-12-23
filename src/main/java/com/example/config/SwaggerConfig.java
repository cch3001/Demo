package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;



@Configuration
@EnableSwagger2 
@Profile({"dev","test"})
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        //
        return new Docket(DocumentationType.SWAGGER_2) 
                .apiInfo(apiInfo())
                .select() 
                .apis(RequestHandlerSelectors.basePackage("com.example.controller")) 
                .paths(PathSelectors.any())
                .build();

    }

    /**
     * 定義API 標題 描述 版本
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Demo Currency API")
                .description("Demo currency description ")
                .termsOfServiceUrl("") 
                .version("1.0") 
                .build(); 
    }
}
