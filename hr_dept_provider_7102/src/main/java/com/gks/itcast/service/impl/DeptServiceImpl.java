/**
 * 
 */
package com.gks.itcast.service.impl;

import com.gks.itcast.Dept;
import com.gks.itcast.PageBean;
import com.gks.itcast.mapper.DeptDao;
import com.gks.itcast.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;


/**
 * @author :���¶���--WXY
 * @motto :Nothing is impossible 2020��4��6��
 */
@Transactional
@Service
public class DeptServiceImpl implements DeptService {
//	@Autowired
//	private EmployeeDao employeeDao;

	@Autowired
	private DeptDao deptDao;

	/**
	 * 
	 */
	public List<Dept> searchDept() {
		// TODO Auto-generated method stub
		List<Dept> deptList = deptDao.findAll();
		return deptList;

	}

	@Override
	public PageBean searchDept(Integer currentPage, Dept dept) {
//		��ѯ��ҳ��ʾ����
		HashMap<String, Object> conditionMap = new HashMap<String, Object>();
		if (dept.getName() != null && dept.getName().trim().length() > 0) {

			conditionMap.put("name", "%" + dept.getName() + "%");
		} else {
			conditionMap.put("name", null);

		}

		// ��ȡ�ܼ�¼��
		Integer totalCount = deptDao.getTotalCount(conditionMap);
//		������ҳʵ��:
		PageBean pageBean = new PageBean(currentPage, totalCount);
//		��ʼ����:
		Integer startIndex = (pageBean.getCurrentPage() - 1) * pageBean.getPageSize();

		conditionMap.put("startIndex", startIndex);
		conditionMap.put("endIndex", pageBean.getPageSize());
		List<Dept> items = deptDao.getItems(conditionMap);
		pageBean.setItems(items);
		pageBean.setTotalCount(totalCount);
		return pageBean;
	}

	@Override
	public Dept getDeptById(Integer id) {
		// TODO Auto-generated method stub
	 Dept dept=	deptDao.getById(id);
		
		
		return dept;
	}

	@Override
	public void update(Dept dept) {
		// TODO Auto-generated method stub
		deptDao.upate(dept);
		
	}

	@Override
	public void add(Dept dept) {
		// TODO Auto-generated method stub
		deptDao.add(dept);
		
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
//		List<Employee> employeeByDeptId = employeeDao.getEmployeeByDeptId(id);
//
//		if(employeeByDeptId.size()>0) {
//			for (Employee employee : employeeByDeptId) {
//				employeeDao.delete(employee.getId());
//			}
//
//		}
		
		deptDao.delete(id);
		
	}

	@Override
	public List<Dept> findAll() {
		// TODO Auto-generated method stub
		List<Dept> depts = deptDao.findAll();
		return depts;
	}

}
