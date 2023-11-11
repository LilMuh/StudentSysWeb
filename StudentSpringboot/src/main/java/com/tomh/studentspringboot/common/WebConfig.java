package com.tomh.studentspringboot.common;

import com.tomh.studentspringboot.exception.CustomException;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Resource
    private JwtInterceptor jwtInterceptor;

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer){
        // Initialize a prefix for each router
        configurer.addPathPrefix("/tomH_Page", clazz -> clazz.isAnnotationPresent(RestController.class));
    }

    // Customize JwtInterceptors, and set rules
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(jwtInterceptor).addPathPatterns("/tomH_Page/**")
                .excludePathPatterns("/tomH_Page/admin/login")
                .excludePathPatterns("/tomH_Page/admin/register")
                .excludePathPatterns("/tomH_Page/admin/forget")
                .excludePathPatterns("/tomH_Page/admin/changePassword");
    }
}
