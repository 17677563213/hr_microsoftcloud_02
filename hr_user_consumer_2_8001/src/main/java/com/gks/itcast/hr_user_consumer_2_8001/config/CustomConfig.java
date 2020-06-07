package com.gks.itcast.hr_user_consumer_2_8001.config;

import com.gks.itcast.hr_user_consumer_2_8001.interceptor.customInterceptor;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: 月下独酌
 * @version: 1.0
 * @QQ: 3268761517
 */
@Configuration
public class CustomConfig {
    @LoadBalanced
    @Bean
    public RestTemplate getRestTemplate(){

       return  new RestTemplate();
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {


        return new WebMvcConfigurer() {



            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new customInterceptor())
                        //指定要拦截的请求 /** 表示拦截所有请求
                        .addPathPatterns("/**")

                        .excludePathPatterns( "/toLogin","/user/login",
                                "/css/**","/images/**","/js/**"
                        );


            }
        };
    }
}
