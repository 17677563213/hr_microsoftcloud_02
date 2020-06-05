package com.gks.itcast.mapper;


import com.gks.itcast.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author :月下独酌--WXY
 * @motto :Nothing is impossible
 * 2020年3月28日
 */


@Component
public interface UserDao {

	@Select("select * from user_inf where loginname=#{loginName} and password=#{password}")
	public User getById(User user);

	@Select("select count(id) from user_inf where username like '%${arg0}%' and status like '%${arg1}%'")
	public Integer getTotalCount(String username, String status);

	/**
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	@Select("select * from user_inf  where username like '%${arg0}%' and status like '%${arg1}%' limit #{arg2},#{arg3}")
	public List<User> getList(String username, String status, Integer startIndex, Integer pageSize);

	/**
	 * @param user
	 */

	@Insert("insert into user_inf(username,status,loginName,password,createDate) values(#{username},#{status},#{loginName},#{password},#{createDate})")
	public void addUser(User user);

	/**
	 * @param id
	 * @return
	 */
	@Select("select * from user_inf where id=#{arg0}")
	public User getUserById(String id);

	/**
	 * @param user
	 */
	@Update("update user_inf set loginName=#{loginName},username=#{username},password=#{password},status=#{status} where id=#{id}")
	public void updateUser(User user);

	/**
	 * @param id
	 */
	@Delete("delete from user_inf where id=#{arg0}")
	public void delete(int id);
}
