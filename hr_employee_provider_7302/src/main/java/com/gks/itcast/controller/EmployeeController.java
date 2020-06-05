/**
 * 
 */
package com.gks.itcast.controller;

import com.gks.itcast.PageBean;
import com.gks.itcast.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * @author :���¶���--WXY
 * @motto  :Nothing is impossible
 * 2020��4��7��
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	
	
	@RequestMapping(value="test",method = RequestMethod.GET)
	public void test() {
		employeeService.test();
		System.out.println("���Ʋ�"+employeeService);
		
		
	}
	/**
	 * ��ҳ������ѯ
	 * @param currentPage
	 * @param conditionMap
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/searchEmployee/{currentPage}",method = {RequestMethod.GET,RequestMethod.POST})
	public PageBean searchEmployee(@PathVariable("currentPage") Integer currentPage,@RequestParam Map<Object, Object> conditionMap,HttpServletRequest request,@RequestParam Map<Object, Object> tipMap) {
		System.out.println(conditionMap);
		PageBean pageBean = employeeService.searchEmployee(currentPage, conditionMap);
//		List<Dept> depts = deptService.findAll();
//		List<Job> jobs = jopService.findAll();
		request.setAttribute("pageBean", pageBean);
//		request.setAttribute("jobs", jobs);
//		request.setAttribute("depts", depts);
		request.setAttribute("tipMap", tipMap);
		
		
		 
		
		
		return pageBean;
		
	}
	/**
	 * ��ת������ҳ��
	 * @param id
	 * @param request
	 * @return
	 */
//	@RequestMapping(value="/toUpdateEmployee/{id}",method = RequestMethod.GET)
//	public String toUpdateEmployee(@PathVariable("id") Integer id ,HttpServletRequest request) {
//
//		Employee employee = employeeService.getByIdEmployee(id);
//		request.setAttribute("employee", employee);
//		List<Dept> depts = deptService.findAll();
//		List<Job> jobs = jopService.findAll();
//		request.setAttribute("jobs", jobs);
//		request.setAttribute("depts", depts);
//		return "employee/showUpdateEmployee";
//	}
	/**
	 * ����
	 * @param mapEmployee
	 * @return
	 */
	@RequestMapping(value="/updateEmployee",method = RequestMethod.POST)
	public String updateEmployee(@RequestParam Map<Object, Object> mapEmployee) {
		
		 
		employeeService.update(mapEmployee);
		System.out.println(mapEmployee);
		
		return "redirect:searchEmployee/1";
		
		
	}
	/**
	 * ��ת�����ҳ��
	 * @param request
	 * @return
	 */
//	@RequestMapping(value="/addEmployee",method = RequestMethod.GET)
//	public String toAddEmployee(HttpServletRequest request) {
//		List<Dept> depts = deptService.findAll();
//		List<Job> jobs = jopService.findAll();
//
//		request.setAttribute("jobs", jobs);
//		request.setAttribute("depts", depts);
//
//		return "employee/showAddEmployee";
//	}
	/**
	 * ���
	 * @param mapEmployee
	 * @return
	 */
	@RequestMapping(value="/addEmployee",method = RequestMethod.POST)
	public String add(@RequestParam Map<Object, Object> mapEmployee) {
		
		
		employeeService.add(mapEmployee);
		
		
		return "redirect:searchEmployee/1";
		
	}
	@RequestMapping(value="/delete",method = RequestMethod.GET)
	public String delete(String ids) {
		String[] idArray = ids.split(",");
		System.out.println("=========================++++++++++++");
		System.out.println(idArray);
		for (int id = 0; id < idArray.length; id++) {

			employeeService.delete(new Integer(idArray[id]));

		}
		
		return "redirect:searchEmployee/1";
	}


}
