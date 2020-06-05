/**
 * 
 */
package com.gks.itcast.service.impl;

import com.gks.itcast.Document;
import com.gks.itcast.PageBean;
import com.gks.itcast.mapper.DocumentDao;
import com.gks.itcast.service.DocumentService;
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
public class DocumentServiceImpl implements DocumentService {
	@Autowired
	private DocumentDao documentDao;

	@Override
	public void test() {
		// TODO Auto-generated method stub
		System.out.println("�־ò����"+documentDao);
		
	}

	@Override
	public PageBean searchDocument(Map<Object, Object> conditionMap, Integer currentPage) {
	 
//		�ж��Ƿ��װ
		if(conditionMap.get("title")==null) {
			conditionMap.put("title", null);
		}else {
			if(conditionMap.get("title").equals("")) {
				conditionMap.put("title", null);
			}else {
				String title = (String) conditionMap.get("title");
				conditionMap.put("title", "%"+title+"%");
			}
		}
//		��ȡ�ܼ�¼��:
		Integer totalCount = documentDao.getTotalCount(conditionMap);
//		������ҳʵ��:
		PageBean pageBean = new PageBean(currentPage, totalCount);
//		��ʼ����:
		Integer startIndex = (pageBean.getCurrentPage() - 1) * pageBean.getPageSize();

		conditionMap.put("startIndex", startIndex);
		conditionMap.put("endIndex", pageBean.getPageSize());
		List<Document> items = documentDao.getItems(conditionMap);
		pageBean.setItems(items);
		pageBean.setTotalCount(totalCount);
		return pageBean;
 
	}

	@Override
	public Document getDocumentById(Integer id) {
		// TODO Auto-generated method stub
		Document document = documentDao.getDocumentById(id);
		return document;
	}

	@Override
	public void update(Document document) {
		
		
		documentDao.update(document);
		
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		documentDao.delete(id);
	}

	@Override
	public void add(Document document) {
		// TODO Auto-generated method stub
		documentDao.add(document);
		
	}
	
	

}
