package com.example.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("user-page");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("user").setViewName("user-page");
        registry.addViewController("/all-user").setViewName("/all-user-page");
        registry.addViewController("/create-user").setViewName("/create-user-page");
    }

}