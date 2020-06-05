package com.gks.itcast.controller;


import com.gks.itcast.PageBean;
import com.gks.itcast.User;
import com.gks.itcast.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author :月下独酌--WXY
 * @motto :Nothing is impossible
 * 2020年3月28日
 */



@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	/**
	 * 删除
	 *
	 * @param ids
	 * @return
	 */

	@RequestMapping(value = "/addUser/{ids}", method = RequestMethod.DELETE)

	public void removeUser(@PathVariable("ids") String ids) {
		System.out.println("需要删除的编号" + ids);
		String[] idArray = ids.split(",");
		System.out.println("=========================++++++++++++");
		System.out.println(idArray);
		for (int id = 0; id < idArray.length; id++) {

			userService.delete(new Integer(idArray[id]));

		}


	}

	/**
	 * 用户登录
	 *
	 * @param user
	 *
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	@ResponseBody
	public User getById( @RequestBody User user) throws IOException {

		User user1 = userService.getById(user);
		System.out.println("userprovider"+user);


		return user1;


	}

	/**
	 * 跳转到对应页面
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/loginForm", "/left", "/top", "/right" })
	public String toLogin(HttpServletRequest request) {
		String url = request.getRequestURI();
		System.out.println("url: " + url);
		String page = url.substring(url.lastIndexOf("/") + 1);
		return page;

	}

	@RequestMapping(value = "/list/{currentPage}")
	@ResponseBody
	public PageBean list(@RequestBody User user, @PathVariable("currentPage") Integer currentPage) {


		PageBean pageBean = userService.list(currentPage, user);
		System.out.println("数据测试"+pageBean.getCurrentPage());
		return pageBean;

	}

	/**
	 * 跳转到添加页面
	 *
	 * @return
	 */
	@RequestMapping(value = "/toAddUser", method = RequestMethod.GET)
	public String toAddUser() {

		return "user/showAddUser";
	}

	/**
	 * 添加
	 *
	 * @param addUser
	 * @return
	 */
	@RequestMapping(value = "/addUser", method = RequestMethod.PUT)
	public void addUser(@RequestBody User addUser) {

		System.out.println("添加的内容============" + addUser);
		if (addUser != null) {
			userService.addUser(addUser);

		}


	}

	/**
	 * 跳转到更新页面
	 *
	 * @param id
	 *
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/toUpdate/{id}", method = RequestMethod.GET)
	public User toUpate(@PathVariable("id") String id) {
		User user = userService.getUserById(id);
	 	return user;

	}

	@RequestMapping(value = "/updateUser", method = RequestMethod.PUT)
	public void updateUser(@RequestBody User user) {
		System.out.println("修改数据===================================" + user);
		userService.updateUser(user);



	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public void logOut(HttpSession session, HttpServletResponse resp, HttpServletRequest req) throws IOException {
		session.removeAttribute("respUser");
		System.out.println();
		resp.sendRedirect(req.getContextPath() + "/index.jsp");

	}

}
