/**
 * 
 */
package com.gks.itcast.service.impl;

import com.gks.itcast.Employee;
import com.gks.itcast.PageBean;
import com.gks.itcast.mapper.EmployeeDao;
import com.gks.itcast.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


/**
 * @author :���¶���--WXY
 * @motto  :Nothing is impossible
 * 2020��4��7��
 */
@Transactional
@Service
public class EmployeeServiceImpl implements EmployeeService {
	 
	
	@Autowired
	private EmployeeDao employeeDao;
	
	
	public void test() {
		System.out.println("�־ò�"+employeeDao);
		
		
	}


	@Override
	public PageBean searchEmployee(Integer currentPage, Map<Object, Object> conditionMap) {
		System.out.println("conditionMap          ++++++++++++"+conditionMap);
		if(conditionMap.get("dept_id")==null) {
			conditionMap.put("dept_id", null);
			conditionMap.put("job_id", null);
			conditionMap.put("sex", null);
			conditionMap.put("name", null);
			conditionMap.put("card_id", null);
			conditionMap.put("phone", null);
		}else {
			if(conditionMap.get("dept_id").equals("0")) {
				
				
				conditionMap.put("dept_id", null);
			}
			if(conditionMap.get("job_id").equals("0")) {
				
				conditionMap.put("job_id", null);
				
			}
			if(conditionMap.get("sex").equals("0")) {
				
				
				conditionMap.put("sex", null);
			}
			if(conditionMap.get("name").equals("")) {
				conditionMap.put("name", null);
				
			}else {
				String name = (String) conditionMap.get("name");
				conditionMap.put("name", "%"+name+"%");
			}
			if(conditionMap.get("phone").equals("")) {
				conditionMap.put("phone", null);
				
			}else {
				String phone = (String) conditionMap.get("phone");
				conditionMap.put("phone", "%"+phone+"%");
			}
			if(conditionMap.get("card_id").equals("")) {
				conditionMap.put("card_id", null);
			}else {
				String card_id = (String) conditionMap.get("card_id");
				conditionMap.put("card_id", "%"+card_id+"%");
				
			}
			
			
			
		}


		System.out.println("employeeDao -=-----------------1"+employeeDao);
		// ��ȡ�ܼ�¼��
		Integer totalCount = employeeDao.getTotalCountByCondition(conditionMap);
//		������ҳʵ��:
		PageBean pageBean = new PageBean(currentPage, totalCount);
		pageBean.setPageSize(2);

//		��ʼ����:
		Integer startIndex = (pageBean.getCurrentPage() - 1) * pageBean.getPageSize();

		conditionMap.put("startIndex", startIndex);
		conditionMap.put("endIndex", pageBean.getPageSize());


		List<Employee> items = employeeDao.getItmes(conditionMap);
		pageBean.setItems(items);
		pageBean.setTotalCount(totalCount);
		return pageBean;
	}


	@Override
	public Employee getByIdEmployee(Integer id) {
		// TODO Auto-generated method stub
		Employee employee = employeeDao.getByIdEmployee(id);
		return employee;
	}


	@Override
	public void update(Map<Object, Object> employeeMap) {
		// TODO Auto-generated method stub
		employeeDao.update(employeeMap);
		
	}


	@Override
	public void add(Map<Object, Object> employeeMap) {
		// TODO Auto-generated method stub
		employeeDao.add(employeeMap);
	}


	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		employeeDao.delete(id);
		
	}

	@Override
	public void deleteByJid(Integer jid) {
		employeeDao.deleteByJid(jid);
	}

	@Override
	public void deleteByEid(Integer eid) {
		employeeDao.deleteByEid(eid);

	}
	

}
