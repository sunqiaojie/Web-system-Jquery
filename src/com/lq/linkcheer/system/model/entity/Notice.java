package com.lq.linkcheer.system.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;



/**
 * 通知表
 * @author Administrator
 *
 */

@Entity
@Table(name = "t_common_notice")
public class Notice implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//招聘id   id
	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@GeneratedValue(generator = "uuid")
	private String id;
	
	//通知标题  title
	@Column(name = "title")
	private String title;
	
	//通知时间段  time_limit
	@Column(name = "time_limit")
	private String time_limit;
	
	//通知内容  content
	@Column(name = "content")
	private String content;
	
	//添加记录时间
	@Column(name="time")
	private Date time;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTime_limit() {
		return time_limit;
	}

	public void setTime_limit(String time_limit) {
		this.time_limit = time_limit;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
}
