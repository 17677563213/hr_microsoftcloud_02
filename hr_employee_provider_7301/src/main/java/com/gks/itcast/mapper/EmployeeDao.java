/**
 * 
 */
package com.gks.itcast.mapper;


import com.gks.itcast.Employee;
import org.apache.ibatis.annotations.Delete;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


/**
 * @author :���¶���--WXY
 * @motto  :Nothing is impossible
 * 2020��4��7��
 */
@Component
public interface EmployeeDao {
	
	public void test();
	
	
	public List<Employee> getItmes(Map<Object, Object> conditionMap);
	
	public Integer getTotalCountByCondition(Map<Object, Object> conditionMap);
	
	
	public Employee getByIdEmployee(Integer id);
	
	public void update(Map<Object, Object> employeeMap);
	
	public void add(Map<Object, Object> employeeMap);
	
	public void delete(Integer id);
	
	public List<Employee> getEmployeeByDeptId(Integer deptId);
	public List<Employee> getEmployeeByJobId(Integer jobId);
	@Delete("delete from employee_inf where JOB_ID=#{jid}")
	public void deleteByJid (Integer jid);
	@Delete("delete from employee_inf where DEPT_ID=#{eid}")
	public void deleteByEid(Integer eid);
	

}
