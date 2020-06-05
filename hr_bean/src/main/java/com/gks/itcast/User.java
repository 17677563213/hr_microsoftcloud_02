package com.gks.itcast;
/**
 * @author :���¶���--WXY
 * @motto  :Nothing is impossible
 * 2020��3��28��
 */

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;


public class User implements Serializable{
	
	private Integer id;
	
	private String loginName;
	
	private String password;
	
	private String status;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createDate;
	
	private String username;

	@Override
	public String toString() {
		return "User [id=" + id + ", loginName=" + loginName + ", password=" + password + ", status=" + status
				+ ", createDate=" + createDate + ", username=" + username + "]";
	}

	public User(Integer id, String loginName, String password, String status, Date createDate, String username) {
		super();
		this.id = id;
		this.loginName = loginName;
		this.password = password;
		this.status = status;
		this.createDate = createDate;
		this.username = username;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
 
	
	

}
