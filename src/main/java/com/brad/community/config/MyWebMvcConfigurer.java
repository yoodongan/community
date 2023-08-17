package com.brad.community.config;

import com.brad.community.interceptor.BeforeActionInterceptor;
import com.brad.community.interceptor.BeforeLoginInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class MyWebMvcConfigurer implements WebMvcConfigurer {
    private final BeforeActionInterceptor beforeActionInterceptor;
    private final BeforeLoginInterceptor beforeLoginInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(beforeActionInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/resources/**")
                .excludePathPatterns("/error");

        registry.addInterceptor(beforeLoginInterceptor)
                .addPathPatterns("/usr/article/write").addPathPatterns("/usr/article/doWrite")
                .addPathPatterns("/usr/article/modify").addPathPatterns("/usr/article/doModify")
                .addPathPatterns("/usr/article/doDelete");
    }
}
