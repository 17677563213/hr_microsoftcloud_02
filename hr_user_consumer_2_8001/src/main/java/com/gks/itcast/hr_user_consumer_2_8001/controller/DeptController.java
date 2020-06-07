package com.gks.itcast.hr_user_consumer_2_8001.controller;

import com.gks.itcast.Dept;
import com.gks.itcast.PageBean;
import com.gks.itcast.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author: 月下独酌
 * @version: 1.0
 * @QQ: 3268761517
 */
@Controller
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private RestTemplate restTemplate;

    private final static  String REST_URL_PREFIX="http://microservice-deptService";

    private final static  String REST_URL_PREFIX_EMPLOYEE="http://microservice-employeeService";


    @RequestMapping(value="/selectDept/{currentPage}",method = {RequestMethod.GET,RequestMethod.POST} )
    public String toDept(HttpServletRequest request, Dept dept, @PathVariable("currentPage") Integer currentPage, @RequestParam Map<Object, Object>conditionMap, Model model) {
        System.out.println("需要查询的条件"+dept.getName());

        model.addAttribute("tipsDept",conditionMap);
        PageBean pageBean = restTemplate.postForObject(REST_URL_PREFIX + "/dept/selectDept/"+currentPage, dept, PageBean.class);
        System.out.println("分页数据");
        System.out.println(pageBean);
        model.addAttribute("pageBean",pageBean);


        return "dept/dept";


    }
    @RequestMapping("/toUpdateDept/{id}")
    public String toUpdateDept(@PathVariable("id") Integer id,Model model){
        System.out.println("updateDept ::id"+id);
        Dept dept = restTemplate.getForObject(REST_URL_PREFIX + "/dept/toUpdateDept/"+id, Dept.class);
        model.addAttribute("dept",dept);
        System.out.println("update"+dept);
        return "dept/showUpdateDept";
    }
    @RequestMapping(value = "/updateDept",method = RequestMethod.POST)
    public String updateUser(Dept dept){

        restTemplate.put(REST_URL_PREFIX + "/dept/updateDept", dept);
        return "redirect:/dept/selectDept/1";



    }
    @RequestMapping(value = "/removeDept/{ids}")
    public String removeDept(@PathVariable("ids") String ids,HttpServletRequest request) throws InterruptedException {
        System.out.println("ids"+ids);
        HttpSession session = request.getSession();
        User respUser = (User) session.getAttribute("respUser");
        restTemplate.delete(REST_URL_PREFIX_EMPLOYEE+"/employee/deleteByEid/"+ids);

        Thread.sleep(1000);

       restTemplate.delete(REST_URL_PREFIX + "/dept/removeDept/" + ids);

        System.out.println("////////////////////aaaa");

        return "redirect:/dept/selectDept/1";

    }
    @RequestMapping("/toAdd")
    public String toadd(){

        return "dept/showAddDept";
    }
    @RequestMapping("/addDept")
    public String addDept(Dept dept){
        System.out.println("add : "+dept);
        Boolean aBoolean = restTemplate.postForObject(REST_URL_PREFIX + "/dept/addDept", dept, boolean.class);


        return "redirect:/dept/selectDept/1";

    }


}
