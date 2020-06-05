/**
 * 
 */
package com.gks.itcast.controller;

import com.gks.itcast.Job;
import com.gks.itcast.PageBean;
import com.gks.itcast.service.JopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


/**
 * @author :���¶���--WXY
 * @motto  :Nothing is impossible
 * 2020��4��7��
 */
@Controller
@RequestMapping("/job")
public class JobController {
	@Autowired
	private JopService jobService;
	
	@RequestMapping(value="/test",method = RequestMethod.GET)
	public String  test() {
		jobService.test();
		System.out.println("ҵ������"+jobService);
		
		return null;
		
	}
	@ResponseBody
	@RequestMapping(value="/selectJob/{currentPage}",method = {RequestMethod.GET,RequestMethod.POST} )
	public PageBean PageBean(HttpServletRequest request,Job job,@PathVariable("currentPage") Integer currentPage) {
	  
		
		
		 PageBean pageBean=jobService.searchJob(currentPage,job);
		 System.out.println("��ҳ����");
		 System.out.println(pageBean);
 
	 
		 
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("tipsJob", job);
		 
		
		return pageBean;
		
		
	}
	/**
	 * id��ѯ
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/toUpdateJob/{id}",method = RequestMethod.GET)
	public String toUpdateJob(@PathVariable("id") Integer id,HttpServletRequest request) {
		Job job =jobService.getJobById(id);
	 
		
		request.setAttribute("job", job);
		
		
		
		
		return "job/showUpdateJob";
	}
	/**
	 * ����
	 * @param job
	 * @return
	 */
	@RequestMapping(value="/updateJob",method = RequestMethod.POST)
	public String updateJob(Job job) {
		 
		jobService.update(job);
		
		return "redirect:selectJob/1";
		
	}
	@RequestMapping(value="/toAddJob",method = RequestMethod.GET)
	public String toAdd() {
		
		
		return "job/showAddJob";
	}
	@RequestMapping(value="addJob",method = RequestMethod.POST)
	public String addJob(Job job) {
		System.out.println("��Ӳ���!");
		System.out.println(job);
		
		jobService.add(job);
		return "redirect:selectJob/1";
	}
	
	@RequestMapping(value="/removeJob",method = RequestMethod.GET)
	public String removeJob(String ids) {
		
		System.out.println("��Ҫɾ���ı��" + ids);
		String[] idArray = ids.split(",");
		System.out.println("=========================++++++++++++");
		System.out.println(idArray);
		for (int id = 0; id < idArray.length; id++) {

			jobService.delete(new Integer(idArray[id]));

		}

		return "redirect:selectJob/1";
		
		 
	}

}
