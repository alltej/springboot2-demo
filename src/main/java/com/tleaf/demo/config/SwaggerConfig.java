package com.tleaf.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Optional;

/**
 * @author Allan Tejano
 * 4/22/2018
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket( DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis( RequestHandlerSelectors.basePackage("com.tleaf.demo.controllers"))
                .paths( PathSelectors.any())
                .build()
                .genericModelSubstitutes(Optional.class)
                .pathMapping("/");
    }

    private ApiInfo apiInfo() {
        return new ApiInfo("Rest Demo", "", "1.0", "",
                new Contact("", "", ""), "", "", new ArrayList());
    }
}
