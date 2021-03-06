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
	@ResponseBody
	@RequestMapping(value="/addEmployee",method = RequestMethod.PUT)
	public void add(@RequestBody Map<Object, Object> mapEmployee) {


		employeeService.add(mapEmployee);




	}
	@ResponseBody
	@RequestMapping(value="/delete/{ids}",method = RequestMethod.DELETE)
	public void delete(@PathVariable("ids") String ids) {
		String[] idArray = ids.split(",");
		System.out.println("=========================++++++++++++");
		System.out.println(idArray);
		for (int id = 0; id < idArray.length; id++) {

			employeeService.delete(new Integer(idArray[id]));

		}
		

	}

	@ResponseBody
	@RequestMapping(value = "/deleteByJid/{jid}",method = RequestMethod.DELETE)
	public void deleteByJid(@PathVariable("jid") String jid) {
		String[] idArray = jid.split(",");
		System.out.println("=========================++++++++++++");
		System.out.println(idArray);
		for (int id = 0; id < idArray.length; id++) {
			employeeService.deleteByJid(new Integer(idArray[id]));



		}





	}

	@ResponseBody
	@RequestMapping(value = "/deleteByEid/{eid}",method = RequestMethod.DELETE)
	public void deleteByEid(@PathVariable("eid") String eid) {
		String[] idArray = eid.split(",");
		System.out.println("=========================++++++++++++");
		System.out.println(idArray);
		for (int id = 0; id < idArray.length; id++) {
			employeeService.deleteByEid(new Integer(idArray[id]));


		}


	}


}
