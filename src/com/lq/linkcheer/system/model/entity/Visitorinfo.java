package com.lq.linkcheer.system.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="t_common_visitorinfo")
public class Visitorinfo implements Serializable {

	private static final long serialVersionUID = 1L;

	//id  id
	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@GeneratedValue(generator = "uuid")
	private String id;
	
	//游客姓名  name
	@Column(name = "name")
	private String name;
	
	//公司名称  company
	@Column(name = "company")
	private String company;
	
	//工作职务  job
	@Column(name = "job")
	private String job;
	
	//提交日期  time
	@Column(name = "time")
	private Date time;
	
	//手机号  telephone
	@Column(name = "telephone")
	private String telephone;
	
	//意向  intention
	@Column(name = "intention")
	private String intention;
	
	//项目介绍  project
	@Column(name = "project")
	private String project;

	//用户IP地址 ip_add
	@Column(name = "ip_add")
	private String ip_add;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getIntention() {
		return intention;
	}

	public void setIntention(String intention) {
		this.intention = intention;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getIp_add() {
		return ip_add;
	}

	public void setIp_add(String ip_add) {
		this.ip_add = ip_add;
	}
	
}
