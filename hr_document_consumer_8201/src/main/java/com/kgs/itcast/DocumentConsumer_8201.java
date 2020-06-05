package com.kgs.itcast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Auther: 梦学谷
 */
@EnableEurekaClient //标识 是一个Eureka客户端
@SpringBootApplication
public class DocumentConsumer_8201 {

    public static void main(String[] args) {
        SpringApplication.run(DocumentConsumer_8201.class, args);
    }

}
