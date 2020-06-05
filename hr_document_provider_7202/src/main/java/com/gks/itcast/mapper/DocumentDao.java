/**
 * 
 */
package com.gks.itcast.mapper;

import com.gks.itcast.Document;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


/**
 * @author :���¶���--WXY
 * @motto  :Nothing is impossible
 * 2020��4��8��
 */
@Component
public interface DocumentDao {
	
	
	public List<Document> getItems(Map<Object, Object> conditionMap);
	
	public Integer getTotalCount(Map<Object, Object> conditionMap);
	@Select("select * from document_inf where id=#{id}")
	public Document getDocumentById(Integer id);
	
	
	public void update(Document document);
	@Delete("delete from document_inf where id=#{id}")
	public void delete(Integer id);
	
	public void add(Document document);

}
