package com.gks.itcast.hr_user_consumer_2_8001.controller;

import com.gks.itcast.PageBean;
import com.gks.itcast.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author: 月下独酌
 * @version: 1.0
 * @QQ: 3268761517
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    private final static  String REST_URL_PREFIX="http://microservice-userService";
    private final static  String REST_URL_PREFIX_DOCUMENT="http://microservice-documentService";
    private final static  String REST_URL_PREFIX_NOTICE="http://microservice-noticeService";

    @RequestMapping("/list/")
    public PageBean method02(){
        System.out.println("对象测试 : "+restTemplate);
        PageBean pagebean = restTemplate.getForObject(REST_URL_PREFIX + "/user/list/1", PageBean.class);
        return pagebean;

    }
    @RequestMapping("/login")
    public String login(User user, HttpServletResponse response, HttpServletRequest request){
        System.out.println("//////////");
        System.out.println(user);
        HttpSession session = request.getSession();
        if (user.getPassword() != null && user.getPassword().trim().length() > 0 && user.getLoginName() != null
                && user.getLoginName().trim().length() > 0) {
            User respUser = restTemplate.postForObject(REST_URL_PREFIX + "/user/login", (Object) user, User.class);

            if(respUser==null) {
                session.setAttribute("tips", "用户名或密码错误!");
               return "redirect:/toLogin";
            }else {
                session.setAttribute("respUser", respUser);
                request.setAttribute("msg", "成功查询到用户信息");
                session.removeAttribute("tips");
                return "main";

            }

        } else
            request.setAttribute("msg", "登录失败");
        return "loginForm";




    }

    @RequestMapping(value = "/list/{currentPage}", method = { RequestMethod.GET, RequestMethod.POST })
    public String list(@PathVariable("currentPage") Integer currentPage, User user, Model model) {




        PageBean pageBean = restTemplate.postForObject(REST_URL_PREFIX + "/user/list/"+currentPage, user, PageBean.class);

        model.addAttribute("pageBean",pageBean);
        model.addAttribute("user",user);


        return "user/user";

    }
    @RequestMapping("/toUpdate/{id}")
    public String toUpdate(@PathVariable("id") Integer id,Model model){

        User respUser = restTemplate.getForObject(REST_URL_PREFIX + "/user/toUpdate/"+id, User.class);
        model.addAttribute("respUser",respUser);
        System.out.println("update"+respUser);

        return "/user/showUpdateUser";

    }
    @RequestMapping(value = "/updateUser",method = RequestMethod.POST)
    public String updateUser(User user){

    restTemplate.put(REST_URL_PREFIX + "/user/updateUser", user);


    return "redirect:/user/list/1";



    }
    @RequestMapping("/toAddUser")
    public String  toAddUser(){


        return "user/showAddUser";




    }

    @RequestMapping("/addUser")
    public String  addUser(User user){
        System.out.println("添加的数据"+user);
        restTemplate.put(REST_URL_PREFIX + "/user/addUser", user);
        return "redirect:/user/list/1";


    }
    @RequestMapping(value = "/removeUser/{ids}")
    public String removeUser(@PathVariable("ids") String ids, HttpServletRequest request) throws InterruptedException {
        HttpSession session = request.getSession();
        System.out.println("这是编号"+ids);

        User respUser = (User) session.getAttribute("respUser");

        Boolean aBoolean = restTemplate.getForObject(REST_URL_PREFIX_DOCUMENT + "/document/deleteByUid/" + ids, Boolean.class);
         restTemplate.delete(REST_URL_PREFIX_NOTICE + "/notice/deleteByUid/" + ids, Boolean.class);
         Thread.sleep(100);
        if(aBoolean){

            restTemplate.delete(REST_URL_PREFIX + "/user/addUser/"+ids);

        }




        return "redirect:/user/list/1";


    }
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
      session.removeAttribute("respUser");
      return "redirect:/toLogin";



    }










}
