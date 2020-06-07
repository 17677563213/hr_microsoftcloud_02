/**
 * 
 */
package com.gks.itcast.service;

import com.gks.itcast.Document;
import com.gks.itcast.PageBean;

import java.util.Map;


/**
 * @author :���¶���--WXY
 * @motto  :Nothing is impossible
 * 2020��4��8��
 */
public interface DocumentService {
	
	public void test();
	
	public PageBean searchDocument(Map<Object, Object> conditionMap, Integer currentPage);
	
	public Document getDocumentById(Integer id);
	
	public void update(Document document);
	
	public void delete(Integer id);
	
	public void add(Document document);

	public void deleteByUid(Integer uid);

}
