package com.gks.itcast.hr_user_consumer_2_8001.controller;

import com.gks.itcast.Notice;
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

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: 月下独酌
 * @version: 1.0
 * @QQ: 3268761517
 */
@Controller
@RequestMapping("/notice")
public class NoticeController {
    @Autowired
    private RestTemplate restTemplate;

    private final static  String REST_URL_PREFIX="http://microservice-noticeService";



    @RequestMapping(value="/selectNotice/{currentPage}",method = {RequestMethod.GET,RequestMethod.POST})
    public String searchNotice (@PathVariable("currentPage") Integer currentPage,@RequestParam Map<Object,Object> conditionMap,Model model) {
        PageBean pagebean = restTemplate.postForObject(REST_URL_PREFIX + "/notice/searchNotice/"+currentPage,conditionMap, PageBean.class);

        if(conditionMap==null){
            conditionMap=new HashMap<Object, Object>();
        }
        String title = (String) conditionMap.get("title");
        String content = (String) conditionMap.get("content");
        if(title==null){
            conditionMap.put("title",null);
        }
        if(content==null){
            conditionMap.put("content",null);
        }
        model.addAttribute("conditionMap",conditionMap);
        model.addAttribute("pageBean",pagebean);
        return "notice/notice";

    }


    @RequestMapping(value="/toAddNotice" ,method = RequestMethod.GET)
    public String toAddNotice() {

        return "notice/showAddNotice";
    }

    @RequestMapping(value="/addNotice",method = RequestMethod.POST)
    public String add(Notice notice, HttpSession session) {
        User sessionUser = (User) session.getAttribute("respUser");
        if(sessionUser!=null&&sessionUser.getId()!=null) {
            notice.setUser(sessionUser);
        }
        restTemplate.put(REST_URL_PREFIX +"/notice/addNotice",notice);

        return "redirect:/notice/selectNotice/1";
    }

    @RequestMapping(value="/toUpdateNotice/{id}",method = RequestMethod.GET)
    public String toUpdateNotice(@PathVariable("id") Integer id,Model model) {
        System.out.println("修改编号"+id);
        Notice notice= restTemplate.getForObject(REST_URL_PREFIX + "/notice/toUpdateNotice/"+id,Notice.class);

       model.addAttribute("notice",notice);



        return "notice/showUpdateNotice";

    }

    @RequestMapping(value="/updateNotice",method = RequestMethod.POST)
    public String updateNotice(Notice notice) {
        restTemplate.put(REST_URL_PREFIX + "/notice/updateNotice/",notice);

        return "redirect:/notice/selectNotice/1";


    }

    @RequestMapping(value="/previewNotice/{id}",method = RequestMethod.GET)
    public String previewNotice(@PathVariable("id") Integer id,Model model) {
        Notice notice= restTemplate.getForObject(REST_URL_PREFIX + "/notice/toUpdateNotice/"+id,Notice.class);
        System.out.println("notice result");
        System.out.println(notice);
         model.addAttribute("notice",notice);
        return "notice/previewNotice";
    }

    @RequestMapping(value="/delete/{ids}",method = RequestMethod.GET)
    public String delete(@PathVariable("ids") String ids) {
       restTemplate.delete(REST_URL_PREFIX + "/notice/delete/"+ids);
        return "redirect:/notice/selectNotice/1";
    }


}
