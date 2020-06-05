package com.kgs.itcast.controller;

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
@RequestMapping("/document")
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    private final static  String REST_URL_PREFIX="http://microservice-documentService";

    @RequestMapping("/method02")
    public PageBean method02(){
        System.out.println("对象测试 : "+restTemplate);
        PageBean pagebean = restTemplate.getForObject(REST_URL_PREFIX + "/document/searchDocument/1", PageBean.class);
        return pagebean;

    }
}
