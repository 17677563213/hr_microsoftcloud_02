package com.gks.itcast.service.Impl;


import com.gks.itcast.PageBean;
import com.gks.itcast.User;
import com.gks.itcast.mapper.UserDao;
import com.gks.itcast.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author :月下独酌--WXY
 * @motto :Nothing is impossible
 * 2020年3月28日
 */


@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;







	/**
	 * 用户登录
	 */
	public User getById(User user) {
		// TODO Auto-generated method stub

		User respUser = userDao.getById(user);

		if(respUser!=null) {
			return respUser;

		}else {

			return null;

		}


	}
	/**
	 * 用户列表
	 */
	public PageBean list(Integer currentPage , User user ) {

		if(user.getUsername()==null) {
			user.setUsername("");

		}
		if(user.getStatus()==null) {
			user.setStatus("");

		}


//		获取总记录数:
		Integer totalCount = userDao.getTotalCount(user.getUsername(),user.getStatus());
		System.out.println("总记录============================="+totalCount);

//		创建分页实例:
		PageBean pageBean = new PageBean(currentPage,totalCount);
//		开始索引:
		Integer startIndex=(pageBean.getCurrentPage()-1)*pageBean.getPageSize();
		System.out.println("条件查询分页测试"+user.getUsername()+"  :  "+user.getStatus()+"  : "+  startIndex+"  : "+pageBean.getPageSize());
//		获取单页显示记录:
		List<User> users=userDao.getList(user.getUsername(),user.getStatus(),   startIndex,pageBean.getPageSize());
//	   	设置分页实例显示记录:
		pageBean.setItems(users);
//	   	设置总记录数:
		pageBean.setTotalCount(totalCount);







		return pageBean;
	}
	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub

		user.setCreateDate(new Date());
		user.setStatus("1");
		userDao.addUser(user);



	}
	@Override
	public User getUserById(String id) {
		// TODO Auto-generated method stub

		User user= userDao.getUserById(id);
		return user;
	}
	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		userDao.updateUser(user);

	}
	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		userDao.delete(id);

	}
	@Override
	public PageBean searchUser(User user) {
		// TODO Auto-generated method stub


		return null;
	}


}
