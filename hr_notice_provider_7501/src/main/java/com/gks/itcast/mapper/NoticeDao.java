/**
 * 
 */
package com.gks.itcast.mapper;

import com.gks.itcast.Notice;
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
public interface NoticeDao {
	
	public List<Notice> getItems(Map<Object, Object> conditionMap);
	
	public Integer getTotalCount(Map<Object, Object> conditionMap);
	
	public void test();
	@Select("select * from notice_inf where id=#{id}")
	public Notice getNoticeById(Integer id);
	
	
	public void add(Notice notice);
	
	public void update(Notice notice);
	@Delete("delete from notice_inf where id=#{id}")
	public void delete(Integer id);

	@Delete("delete from notice_inf where USER_ID=#{uid}")
	public void deleteByUid(Integer uid);

}
