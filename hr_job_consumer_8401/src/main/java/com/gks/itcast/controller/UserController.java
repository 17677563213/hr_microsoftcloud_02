package com.gks.itcast.controller;

import com.gks.itcast.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author: 月下独酌
 * @version: 1.0
 * @QQ: 3268761517
 */
@RestController
@RequestMapping("/job")
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    private final static  String REST_URL_PREFIX="http://microservice-jobService";

    @RequestMapping("/method02")
    public PageBean method02(){
        System.out.println("对象测试 : "+restTemplate);
        System.out.println("employeeConsumer");
        PageBean pagebean = restTemplate.getForObject(REST_URL_PREFIX + "/job/selectJob/1", PageBean.class);
        return pagebean;

    }
}
