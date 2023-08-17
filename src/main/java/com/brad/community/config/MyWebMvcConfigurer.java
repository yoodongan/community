package com.brad.community.config;

import com.brad.community.interceptor.BeforeActionInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class MyWebMvcConfigurer implements WebMvcConfigurer {
    private final BeforeActionInterceptor beforeActionInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(beforeActionInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/resources/**")
                .excludePathPatterns("/error");
    }
}
