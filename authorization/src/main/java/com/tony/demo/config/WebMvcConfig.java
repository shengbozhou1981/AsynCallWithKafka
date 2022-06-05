package com.tony.demo.config;

import com.tony.demo.interceptor.UserLoginInterceptor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan(basePackages = "com.tony.demo") //global exception handel
public class WebMvcConfig implements WebMvcConfigurer {
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
                .excludePathPatterns("/assignment/login","/assignment/logout","/assignment/initialize");//
    }
}

