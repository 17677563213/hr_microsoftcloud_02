/**
 * 
 */
package com.gks.itcast.service;

import com.gks.itcast.Employee;
import com.gks.itcast.PageBean;

import java.util.Map;


/**
 * @author :���¶���--WXY
 * @motto  :Nothing is impossible
 * 2020��4��7��
 */
public interface EmployeeService {
   public void test();
   
   public PageBean searchEmployee(Integer currentPage, Map<Object, Object> conditionMap);
   
   public Employee getByIdEmployee(Integer id);
   
   public void update(Map<Object, Object> employeeMap);
   
   public void add(Map<Object, Object> employeeMap);
   
   public void delete(Integer id);
}
