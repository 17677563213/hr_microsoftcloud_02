package com.gks.itcast;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class Document implements Serializable {

	private Integer id;
	private String title;
	private String filename;
	private String remark;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date create_date;
	private User user;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Document(Integer id, String title, String filename, String remark, Date create_date, User user) {
		this.id = id;
		this.title = title;
		this.filename = filename;
		this.remark = remark;
		this.create_date = create_date;
		this.user = user;
	}

	public Document() {
	}

	@Override
	public String toString() {
		return "Document{" +
				"id=" + id +
				", title='" + title + '\'' +
				", filename='" + filename + '\'' +
				", remark='" + remark + '\'' +
				", create_date=" + create_date +
				", user=" + user +
				'}';
	}
}
