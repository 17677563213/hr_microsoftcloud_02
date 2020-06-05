package com.gks.itcast.hr_user_consumer_2_8001.controller;

import com.gks.itcast.Dept;
import com.gks.itcast.Employee;
import com.gks.itcast.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: 月下独酌
 * @version: 1.0
 * @QQ: 3268761517
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private RestTemplate restTemplate;

    private final static  String REST_URL_PREFIX="http://microservice-employeeService";

    private final static  String REST_URL_PREFIX_DEPT="http://microservice-deptService";


    private final static  String REST_URL_PREFIX_JOB="http://microservice-jobService";

    @RequestMapping(value="/searchEmployee/{currentPage}",method = {RequestMethod.GET,RequestMethod.POST})
    public String searchEmployee(@PathVariable("currentPage") Integer currentPage, Model model, @RequestParam Map<String, Object> conditionMap) {
        System.out.println("conditionMap");
        System.out.println(conditionMap.get("sex"));
        PageBean pagebean = restTemplate.postForObject(REST_URL_PREFIX + "/employee/searchEmployee/"+currentPage,conditionMap, PageBean.class);
        model.addAttribute("pageBean",pagebean);


        List<Dept> depts= restTemplate.getForObject(REST_URL_PREFIX_DEPT + "/dept/findDepts", List.class);
        List<Dept> jobs= restTemplate.getForObject(REST_URL_PREFIX_JOB + "/job/findJobs", List.class);
        model.addAttribute("jobs",jobs);
        model.addAttribute("depts",depts);
        if(conditionMap==null){
            conditionMap=new HashMap<String,Object>();



        }
        String dept_id = (String) conditionMap.get("dept_id");
        String name = (String) conditionMap.get("name");
        String phone = (String) conditionMap.get("phone");
        String sex = (String) conditionMap.get("sex");
        String job_id = (String) conditionMap.get("job_id");
        String card_id = (String) conditionMap.get("card_id");

        if(dept_id==null){
            conditionMap.put("dept_id",null);

        }
        if(name==null){
            conditionMap.put("name",null);

        }
        if(phone==null){
            conditionMap.put("phone",null);

        }
        if(sex==null){
            conditionMap.put("sex",null);

        }
        if(job_id==null){
            conditionMap.put("job_id",null);

        }
        if(card_id==null){
            conditionMap.put("card_id",null);

        }



        model.addAttribute("conditionMap",conditionMap);






        return "employee/employee";

    }
    @RequestMapping("/toUpdateEmployee/{id}")
    public String toUpdateEmployee(@PathVariable("id") Integer id,Model model){

        Employee employee = restTemplate.getForObject(REST_URL_PREFIX + "/employee/toUpdateEmployee/"+id, Employee.class);

        List<Dept> depts= restTemplate.getForObject(REST_URL_PREFIX_DEPT + "/dept/findDepts", List.class);
        List<Dept> jobs= restTemplate.getForObject(REST_URL_PREFIX_JOB + "/job/findJobs", List.class);
        model.addAttribute("jobs",jobs);
        model.addAttribute("depts",depts);
        model.addAttribute("employee",employee);

        Map conditionMap=new HashMap<String,Object>();

        String job_id = (String) conditionMap.get("job_id");


        if(job_id==null){

            conditionMap.put("job_id",employee.getJob().getId());

        }




        model.addAttribute("conditionMap",conditionMap);

        return "employee/showUpdateEmployee";


    }
    @RequestMapping("/updateEmployee")
    public String updateEmployee(@RequestParam Map<Object, Object> mapEmployee){

        restTemplate.put(REST_URL_PREFIX + "/employee/updateEmployee",mapEmployee);

        return "redirect:/employee/searchEmployee/1";



    }
}
