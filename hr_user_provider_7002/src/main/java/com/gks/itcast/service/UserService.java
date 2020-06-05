package com.gks.itcast.service;

import com.gks.itcast.PageBean;
import com.gks.itcast.User;

/**
 * @author :月下独酌--WXY
 * @motto  :Nothing is impossible
 * 2020年3月28日
 */




public interface UserService {


	public User getById(User user);

	/**
	 * @param currentPage
	 * @return
	 */
	public PageBean list(Integer currentPage, User user);

	/**
	 * @param user
	 */
	public void addUser(User user);

	/**
	 * @param id
	 * @return
	 */
	public User getUserById(String id);

	/**
	 * @param user
	 */
	public void updateUser(User user);

	/**
	 * @param id
	 */
	public void delete(int id);

	/**
	 * @param user
	 * @return
	 */
	public PageBean searchUser(User user);
}
