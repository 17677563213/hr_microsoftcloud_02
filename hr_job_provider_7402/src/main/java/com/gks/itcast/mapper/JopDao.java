/**
 * 
 */
package com.gks.itcast.mapper;

import com.gks.itcast.Job;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


/**
 * @author :���¶���--WXY
 * @motto :Nothing is impossible 2020��4��7��
 */
@Component
public interface JopDao {

	public Integer getTotalCount(Map map);

	public List<Job> getItems(Map map);
	
	/**
	 * @param id
	 * @return
	 */
	public Job getJobById(Integer id);

	/**
	 * @param job
	 */
	public void upate(Job job);

	/**
	 * @param job
	 */
	public void add(Job job);

	/**
	 * @param id
	 */

	public void delete(Integer id);
	
	
	public List<Job> findAll();

}
