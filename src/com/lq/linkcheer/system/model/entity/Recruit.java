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
 * 招聘表
 * @author Administrator
 *
 */

@Entity
@Table(name = "t_common_recruit")
public class Recruit implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//招聘id
	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@GeneratedValue(generator = "uuid")
	private String id;
	
	//岗位类型   quarters_type
	@Column(name = "quarters_type")
	private String quarters_type;
	
	//岗位名称   quarters_name
	@Column(name = "quarters_name")
	private String quarters_name;
	
	//岗位内容   quarters_content
	@Column(name = "quarters_content")
	private String quarters_content;
	
	//招聘人数   recruit_num
	@Column(name = "recruit_num")
	private String recruit_num;
	
	//学历要求   degree
	@Column(name = "degree")
	private String degree;
	
	//薪水   salary
	@Column(name = "salary")
	private String salary;
	
	//工作地址   address
	@Column(name = "address")
	private String address;
	
	//发布时间   time
	@Column(name = "time")
	private Date time;
	
	//是否发布   ispublish  默认值：0； 1:发布；0：不发布
	@Column(name = "ispublish", columnDefinition = "varchar(250) default '0'")
	private String ispublish;

	//工作年限 seniority
	@Column(name = "seniority")
	private String seniority;
	
	//任职要求 demand
	@Column(name = "demand")
	private String demand;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

		public String getQuarters_name() {
		return quarters_name;
	}

	public void setQuarters_name(String quarters_name) {
		this.quarters_name = quarters_name;
	}

	public String getRecruit_num() {
		return recruit_num;
	}

	public void setRecruit_num(String recruit_num) {
		this.recruit_num = recruit_num;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getIspublish() {
		return ispublish;
	}

	public void setIspublish(String ispublish) {
		this.ispublish = ispublish;
	}

	public String getQuarters_type() {
		return quarters_type;
	}

	public void setQuarters_type(String quarters_type) {
		this.quarters_type = quarters_type;
	}

	public String getQuarters_content() {
		return quarters_content;
	}

	public void setQuarters_content(String quarters_content) {
		this.quarters_content = quarters_content;
	}

	public String getSeniority() {
		return seniority;
	}

	public void setSeniority(String seniority) {
		this.seniority = seniority;
	}

	public String getDemand() {
		return demand;
	}

	public void setDemand(String demand) {
		this.demand = demand;
	}
	
}
