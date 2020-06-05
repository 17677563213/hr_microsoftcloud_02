/**
 * 
 */
package com.gks.itcast.controller;

import com.gks.itcast.Job;
import com.gks.itcast.PageBean;
import com.gks.itcast.service.JopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
	public PageBean PageBean(@RequestBody Job job, @PathVariable("currentPage") Integer currentPage) {



		PageBean pageBean=jobService.searchJob(currentPage,job);
		System.out.println("��ҳ����");
		System.out.println(pageBean);






		return pageBean;


	}
	/**
	 * id��ѯ
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/toUpdateJob/{id}",method = RequestMethod.GET)
	@ResponseBody
	public Job toUpdateJob(@PathVariable("id") Integer id) {
		Job job =jobService.getJobById(id);
		return job;







	}
	/**
	 * ����
	 * @param job
	 * @return
	 */
	@RequestMapping(value="/updateJob")
	@ResponseBody
	public void updateJob(@RequestBody Job job) {
		 
		jobService.update(job);
		

		
	}
	@RequestMapping(value="/toAddJob",method = RequestMethod.GET)
	public String toAdd() {
		
		
		return "job/showAddJob";
	}
	@ResponseBody
	@RequestMapping(value="addJob",method = RequestMethod.PUT)
	public void addJob(@RequestBody  Job job) {
		System.out.println("��Ӳ���!");
		System.out.println(job);
		
		jobService.add(job);

	}

	@ResponseBody
	@RequestMapping(value="/removeJob/{ids}",method = RequestMethod.DELETE)
	public void removeJob(@PathVariable("ids") String ids) {
		
		System.out.println("��Ҫɾ���ı��" + ids);
		String[] idArray = ids.split(",");
		System.out.println("=========================++++++++++++");
		System.out.println(idArray);
		for (int id = 0; id < idArray.length; id++) {

			jobService.delete(new Integer(idArray[id]));

		}


		
		 
	}


	@ResponseBody
	@RequestMapping("/findJobs")

	public List<Job> findJobs(){

		List<Job> jobs = jobService.findAll();
		return jobs;

	}

}
