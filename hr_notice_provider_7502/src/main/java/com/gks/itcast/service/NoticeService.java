/**
 * 
 */
package com.gks.itcast.service;

import com.gks.itcast.Notice;
import com.gks.itcast.PageBean;

import java.util.Map;


/**
 * @author :���¶���--WXY
 * @motto  :Nothing is impossible
 * 2020��4��8��
 */
public interface NoticeService {
	
	
	public void test();
	
	public PageBean searchNotice(Map<Object, Object> conditionMap, Integer currentPage);
	
	public Notice getNoticeById(Integer id);
	
	public void add(Notice notice);
	
	public void update(Notice notice);
	
	public void delete(Integer id);

}
