package com.amdocs.media.assignment.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan(basePackages = "com.amdocs.media.assignment") //global exception handel
public class UserProfileWebMvcConfig implements WebMvcConfigurer {
    /**
     * self defined interceptor
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserLoginInterceptor())
                .addPathPatterns("/**")
                .addPathPatterns("/assignment-profile/**")
                .excludePathPatterns("/authorization/login","/authorization/logout");//
    }

}
