/**
 * 
 */
package com.gks.itcast.service;


import com.gks.itcast.Dept;
import com.gks.itcast.PageBean;

import java.util.List;

/**
 * @author :���¶���--WXY
 * @motto  :Nothing is impossible
 * 2020��4��6��
 */
public interface DeptService {
	
	public List<Dept> searchDept() ;

	/**
	 * @param currentPage
	 * @return
	 */
	public PageBean searchDept(Integer currentPage, Dept dept);

	/**
	 * @param id
	 * @return
	 */
	public Dept getDeptById(Integer id);

	/**
	 * @param dept
	 */
	public void update(Dept dept);

	/**
	 * @param dept
	 */
	public void add(Dept dept);

	/**
	 * @param integer
	 */
	public void delete(Integer id);
	
	public List<Dept> findAll();
	
	 

}
