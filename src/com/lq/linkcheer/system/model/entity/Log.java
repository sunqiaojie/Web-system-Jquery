package com.lq.linkcheer.system.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/** 日志 */
@Entity
@Table(name = "t_common_log")
public class Log implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@GeneratedValue(generator = "uuid")
	private String id;

	// 记录日志的时间
	@Column(name = "logTime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date logTime;

	// IP
	@Column(name = "ip")
	private String ip;

	// 子系统编号
	@Column(name = "menuNo")
	private String menuNo;

	// 日志的内容
	@Column(name = "content")
	private String content;

	// 记录日志的用户id
	@Column(name = "manId")
	private String manId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getLogTime() {
		return logTime;
	}

	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMenuNo() {
		return menuNo;
	}

	public void setMenuNo(String menuNo) {
		this.menuNo = menuNo;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getManId() {
		return manId;
	}

	public void setManId(String manId) {
		this.manId = manId;
	}

}
