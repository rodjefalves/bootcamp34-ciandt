package com.ciandt.bootcamp.Java.Infra;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport{
    
    @Bean
    public Docket breweryApi(){
        return new  Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.java.BreweryApi"))
        .build()
        .apiInfo(metaInfo());
    }
    private ApiInfo metaInfo(){
        return new ApiInfo(
            "JAVA BreweryApi",
            "API solictation brewery",
            "1.0",
            "Terms of Service",
            new Contact("Ci&T", "ciandt.com", "ciandt@ciandt.com"),
            "Apache License Version 2.0",
            "https://www.apache.org/licesen.html", new ArrayList<>()
        );
    }
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("swagger-ui.html")
            .addResourceLocations("classpath:/META-INF/resources");
        registry.addResourceHandler("/webjars/**")
            .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
