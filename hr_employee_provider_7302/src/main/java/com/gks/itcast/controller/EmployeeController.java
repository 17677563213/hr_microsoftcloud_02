/**
 * 
 */
package com.gks.itcast.controller;

import com.gks.itcast.Employee;
import com.gks.itcast.PageBean;
import com.gks.itcast.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
	 * @return
	 */

	@ResponseBody
	@RequestMapping(value="/searchEmployee/{currentPage}",method = {RequestMethod.GET,RequestMethod.POST})
	public PageBean searchEmployee(@PathVariable("currentPage") Integer currentPage,@RequestBody Map<Object, Object> conditionMap) {

		System.out.println("employee-----------------2");
		PageBean pageBean = employeeService.searchEmployee(currentPage, conditionMap);
		return pageBean;

	}
	/**
	 * ��ת������ҳ��
	 * @param id

	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/toUpdateEmployee/{id}",method = RequestMethod.GET)
	public Employee toUpdateEmployee(@PathVariable("id") Integer id ) {

		Employee employee = employeeService.getByIdEmployee(id);
		return employee;


	}
	/**
	 * ����
	 * @param mapEmployee
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateEmployee",method = RequestMethod.PUT)
	public void updateEmployee(@RequestBody Map<Object, Object> mapEmployee) {
		
		 
		employeeService.update(mapEmployee);
		System.out.println(mapEmployee);
		

		
		
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
