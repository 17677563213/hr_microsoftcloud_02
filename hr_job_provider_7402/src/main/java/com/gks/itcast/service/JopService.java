/**
 * 
 */
package com.gks.itcast.service;

import com.gks.itcast.Job;
import com.gks.itcast.PageBean;

import java.util.List;


/**
 * @author :���¶���--WXY
 * @motto  :Nothing is impossible
 * 2020��4��7��
 */
public interface JopService {
   public void test();
   
   /**
	 * @param currentPage
	 * @return
	 */
	public PageBean searchJob(Integer currentPage, Job job);
	
	/**
	 * @param id
	 * @return
	 */
	public Job getJobById(Integer id);

	/**
	 * @param job
	 */
	public void update(Job job);

	/**
	 * @param job
	 */
	public void add(Job job);

	/**
	 * @param integer
	 */
	public void delete(Integer id);
	
	public List<Job> findAll();
}
