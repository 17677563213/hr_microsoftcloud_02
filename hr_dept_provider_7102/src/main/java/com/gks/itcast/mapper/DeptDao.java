
package com.gks.itcast.mapper;

import com.gks.itcast.Dept;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


/**
 * @author :���¶���--WXY
 * @motto  :Nothing is impossible
 * 2020��4��6��
 */
@Component
public interface DeptDao {
	
 
	public List<Dept> findAll();
	
	public Integer getTotalCount(Map map);


	public List<Dept> getItems(Map map);

	/**
	 * @param id
	 * @return
	 */
	@Select("select * from dept_inf where id=#{id}")
	public Dept getById(Integer id);

	/**
	 * @param dept
	 */
	public void upate(Dept dept);

	/**
	 * @param dept
	 */
	public void add(Dept dept);

	/**
	 * @param id
	 */
	public void delete(Integer id);
	
	 

}
