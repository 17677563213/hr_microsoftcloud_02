package com.kgs.itcast.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

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
}
