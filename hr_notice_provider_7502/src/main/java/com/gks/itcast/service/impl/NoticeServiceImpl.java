/**
 * 
 */
package com.gks.itcast.service.impl;

import com.gks.itcast.Notice;
import com.gks.itcast.PageBean;
import com.gks.itcast.mapper.NoticeDao;
import com.gks.itcast.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


/**
 * @author :���¶���--WXY
 * @motto  :Nothing is impossible
 * 2020��4��8��
 */
@Service
@Transactional
public class NoticeServiceImpl implements NoticeService {
	@Autowired
	private NoticeDao noticeDao;

	@Override
	public void test() {
		// TODO Auto-generated method stub
		
		System.out.println("�־ò�"+noticeDao);
		
	}

	@Override
	public PageBean searchNotice(Map<Object, Object> conditionMap, Integer currentPage) {
		// TODO Auto-generated method stub
		if(conditionMap.get("title")==null) {
			conditionMap.put("id", null);
			conditionMap.put("title", null);
			conditionMap.put("content", null);
			conditionMap.put("create_date", null);
			conditionMap.put("user", null);
			
			
			
		}else {
			if(conditionMap.get("title").equals("")) {
				conditionMap.put("title", null);
				
			}else {
				String title=(String) conditionMap.get("title");
				conditionMap.put("title", "%"+title+"%");
				
			}
			
			
			if(conditionMap.get("content").equals("")) {
				conditionMap.put("content", null);
				
			}else {
			String content=(String)	conditionMap.get("content");
			conditionMap.put("content", "%"+content+"%");
				
				
			}
			
		} 
		//��ȡ�ܼ�¼��:
		
		Integer totalCount = noticeDao.getTotalCount(conditionMap);
		//������ҳ����:
		PageBean pageBean = new PageBean(currentPage, totalCount);
//		��ʼ����:
		Integer startIndex = (pageBean.getCurrentPage() - 1) * pageBean.getPageSize();
		
		conditionMap.put("startIndex", startIndex);
		conditionMap.put("endIndex", pageBean.getPageSize());
		
		List<Notice> items = noticeDao.getItems(conditionMap);
		pageBean.setItems(items);
		pageBean.setTotalCount(totalCount);
		return pageBean;
	}

	@Override
	public Notice getNoticeById(Integer id) {
		// TODO Auto-generated method stub
		Notice notice = noticeDao.getNoticeById(id);
		return notice;
	}

	@Override
	public void add(Notice notice) {
		noticeDao.add(notice);
	}

	@Override
	public void update(Notice notice) {
		// TODO Auto-generated method stub
		noticeDao.update(notice);
		
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		noticeDao.delete(id);
	}

}
