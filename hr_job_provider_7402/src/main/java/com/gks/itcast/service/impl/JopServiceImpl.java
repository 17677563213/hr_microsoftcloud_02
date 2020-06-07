/**
 * 
 */
package com.gks.itcast.service.impl;

import com.gks.itcast.Job;
import com.gks.itcast.PageBean;
import com.gks.itcast.mapper.JopDao;
import com.gks.itcast.service.JopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;


/**
 * @author :���¶���--WXY
 * @motto :Nothing is impossible 2020��4��7��
 */

@Transactional
@Service
public class JopServiceImpl implements JopService {
	@Autowired
	private JopDao jobDao;


	public void test() {
		System.out.println("�־ò�ӿڶ���" + jobDao);

	}

	@Override
	public PageBean searchJob(Integer currentPage, Job job) {
		// TODO Auto-generated method stub
//		��ѯ��ҳ��ʾ����
		HashMap<String, Object> conditionMap = new HashMap<String, Object>();
		if (job.getName() != null && job.getName().trim().length() > 0) {

			conditionMap.put("name", "%" + job.getName() + "%");
		} else {
			conditionMap.put("name", null);

		}

		// ��ȡ�ܼ�¼��
		Integer totalCount = jobDao.getTotalCount(conditionMap);
//		������ҳʵ��:
		PageBean pageBean = new PageBean(currentPage, totalCount);
//		��ʼ����:
		Integer startIndex = (pageBean.getCurrentPage() - 1) * pageBean.getPageSize();

		conditionMap.put("startIndex", startIndex);
		conditionMap.put("endIndex", pageBean.getPageSize());
		List<Job> items = jobDao.getItems(conditionMap);
		pageBean.setItems(items);
		pageBean.setTotalCount(totalCount);
		return pageBean;
	}

	@Override
	public Job getJobById(Integer id) {
		// TODO Auto-generated method stub
		Job job = jobDao.getJobById(id);

		return job;
	}

	@Override
	public void update(Job job) {
		// TODO Auto-generated method stub
		jobDao.upate(job);

	}

	@Override
	public void add(Job job) {
		// TODO Auto-generated method stub
		jobDao.add(job);

	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub


 	jobDao.delete(id);

	}

	@Override
	public List<Job> findAll() {
		// TODO Auto-generated method stub
		List<Job> jobs = jobDao.findAll();
		return jobs;
	}

}
