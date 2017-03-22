package com.lq.linkcheer.system.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


/**
 * 岗位表
 * @author Administrator
 *
 */
@Entity
@Table(name = "t_common_quarters")
public class Quarters implements Serializable {

	private static final long serialVersionUID = 1L;

	
	//岗位id
	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@GeneratedValue(generator = "uuid")
	private String id;
	
	//岗位名称 quarters_name
	@Column(name = "quarters_name")
	private String quarters_name;
	
	//岗位类型 quarters_type
	@Column(name="quarters_type")
	private String quarters_type;
	
	//岗位内容 quarters_content
	@Column(name = "quarters_content")
	private String quarters_content;
	
	//启用状态  1：启用
	@Column(name = "status")
	private String status;
	
	//备注 remark
	@Column(name = "remark")
	private String remark;

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
