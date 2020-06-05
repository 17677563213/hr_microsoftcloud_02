package com.gks.itcast;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.gks.itcast.mapper")
public class DocumenttProvider_7201 {

	public static void main(String[] args) {
		SpringApplication.run(DocumenttProvider_7201.class, args) ;
	}
}
