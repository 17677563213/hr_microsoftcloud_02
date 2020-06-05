package com.gks.itcast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Auther: 梦学谷
 */
@EnableEurekaClient //标识 是一个Eureka客户端
@SpringBootApplication
public class JobConsumer_8401 {

    public static void main(String[] args) {
        SpringApplication.run(JobConsumer_8401.class, args);
    }

}
