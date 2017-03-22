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
 * 相册表
 * @author Administrator
 *
 */

@Entity
@Table(name="t_common_picture")
public class Picture implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//图片id  id
	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@GeneratedValue(generator = "uuid")
	private String id;
	
	//图片名称 name
	@Column(name="name")
	private String name;
	
	//图片大小  size
	@Column(name="size")
	private String size;
	
	//图片路径  path
	@Column(name="path")
	private String path;
	
	//图片类型
	@Column(name = "type")
	private String type;
	
	//上传日期  time
	@Column(name="time")
	private Date time;
	
	//用户id  user_id
	@Column(name="user_id")
	private String user_id;
	
	//用户名 user_name
	@Column(name="user_name")
	private String user_name;
	
	//活动id  activity_id
	@Column(name="activity_id")
	private String activity_id;
	
	//活动名称  activity_name
	@Column(name="activity_name")
	private String activity_name;
	
	//图片是否显示  isshow 1：显示；0：不显示；默认值：1
	@Column(name="isshow", columnDefinition = "varchar(250) default '1'",updatable=false,insertable=false)
	private String isshow;

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

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getActivity_id() {
		return activity_id;
	}

	public void setActivity_id(String activity_id) {
		this.activity_id = activity_id;
	}

	public String getActivity_name() {
		return activity_name;
	}

	public void setActivity_name(String activity_name) {
		this.activity_name = activity_name;
	}

	public String getIsshow() {
		return isshow;
	}

	public void setIsshow(String isshow) {
		this.isshow = isshow;
	}
	
}
