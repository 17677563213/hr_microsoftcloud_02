package com.gks.itcast.hr_user_consumer_2_8001.controller;

import com.gks.itcast.Job;
import com.gks.itcast.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

/**
 * @author: 月下独酌
 * @version: 1.0
 * @QQ: 3268761517
 */
@RequestMapping("/job")
@Controller
public class JobController {
    @Autowired
    private RestTemplate restTemplate;

    private final static  String REST_URL_PREFIX="http://microservice-jobService";



    @RequestMapping(value="/selectJob/{currentPage}",method = {RequestMethod.GET,RequestMethod.POST} )
    public String toJob(Job job, @PathVariable("currentPage") Integer currentPage, Model model) {


        PageBean pageBean = restTemplate.postForObject(REST_URL_PREFIX + "/job/selectJob/"+currentPage, job, PageBean.class);


        System.out.println("分页数据");
        model.addAttribute("pageBean",pageBean);
        System.out.println(pageBean);






        return "job/job";


    }
    @RequestMapping("/toUpdateJob/{id}")
    public String toUpdateJob(@PathVariable("id") Integer id ,Model model){
        Job respJob = restTemplate.getForObject(REST_URL_PREFIX + "/job/toUpdateJob/"+id, Job.class);

       model.addAttribute("job", respJob);
       return "job/showUpdateJob";


    }
    @RequestMapping("/updateJob")
    public String updateJob(Job job){
        System.out.println("updateJOb :");
        System.out.println(job);
        restTemplate.put(REST_URL_PREFIX + "/job/updateJob",job);

        return "redirect:/job/selectJob/1";


    }

    @RequestMapping(value="/toAddJob",method = RequestMethod.GET)
    public String toAdd() {


        return "job/showAddDept";
    }

    @RequestMapping("/addJob")
    public String addJob(Job job){
        restTemplate.put(REST_URL_PREFIX + "/job/addJob",job);
        return "redirect:/job/selectJob/1";
    }

    @RequestMapping(value = "/removeJob/{ids}")
    public String removeUser(@PathVariable("ids") String ids){
        System.out.println("这是编号"+ids);
        restTemplate.delete(REST_URL_PREFIX + "/job/removeJob/"+ids);


        return "redirect:/job/selectJob/1";


    }
}
