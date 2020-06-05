/**
 * 
 */
package com.gks.itcast.controller;

import com.gks.itcast.Dept;
import com.gks.itcast.PageBean;
import com.gks.itcast.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


/**
 * @author :���¶���--WXY
 * @motto  :Nothing is impossible
 * 2020��4��6��
 */
@Controller
@RequestMapping("/dept")
public class DeptController {
	@Autowired
	private DeptService deptService;
	
	
	/**
	 *  ��ҳģ����ѯ
	 * @param request
	 * @param dept
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/selectDept/{currentPage}",method = {RequestMethod.GET,RequestMethod.POST} )
	public PageBean toDept(HttpServletRequest request,@RequestBody Dept dept, @PathVariable("currentPage") Integer currentPage, @RequestParam Map<Object, Object>conditionMap) {
		System.out.println("��Ҫ��ѯ������"+dept.getName());

		request.setAttribute("tipsDept", conditionMap);

		 PageBean pageBean=deptService.searchDept(currentPage,dept);
		 System.out.println("��ҳ����");
		 System.out.println(pageBean);



		request.setAttribute("pageBean", pageBean);


		return pageBean;


	}
	/**
	 * id��ѯ
	 * @param id
	 * @return
	 */

	@ResponseBody
	@RequestMapping(value="/toUpdateDept/{id}",method = RequestMethod.GET)
	public Dept toUpdateDept(@PathVariable("id") Integer id) {
		System.out.println("deptProvider id  :"+id);
		Dept dept =deptService.getDeptById(id);







		return dept;
	}
	/**
	 * ����
	 * @param dept
	 * @return
	 */
	@RequestMapping(value="/updateDept",method = RequestMethod.PUT)
	public void updateDept(@RequestBody Dept dept) {
		 
		deptService.update(dept);
		

		
	}
	@RequestMapping(value="/toAddDept",method = RequestMethod.GET)
	public String toAdd() {
		
		
		return "dept/showAddDept";
	}
	@RequestMapping(value="/addDept")
	@ResponseBody
	public boolean addDept(@RequestBody Dept dept) {

		System.out.println(dept);
		
		deptService.add(dept);
		return true;
	}

	@RequestMapping(value="/removeDept/{ids}",method = RequestMethod.DELETE)
	@ResponseBody
	public  boolean removeDept(@PathVariable("ids") String ids) {


		String[] idArray = ids.split(",");
		System.out.println("=========================++++++++++++");
		System.out.println(idArray);
		for (int id = 0; id < idArray.length; id++) {

			deptService.delete(new Integer(idArray[id]));

		}
		return true;



	}
	@ResponseBody
	@RequestMapping("/findDepts")
	public List<Dept> findAll(){
		List<Dept> depts = deptService.findAll();


		return depts;


	}

}
