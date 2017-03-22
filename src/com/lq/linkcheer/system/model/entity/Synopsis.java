package com.lq.linkcheer.system.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


/**
 * 公司简介表
 * @author Administrator
 *
 */

@Entity
@Table(name = "t_common_synopsis")
public class Synopsis implements Serializable {

	private static final long serialVersionUID = 1L;

	//简介ID
	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@GeneratedValue(generator = "uuid")
	private String id;
	
	//简介标题
	@Column(name = "title")
	private String title;
	
	
	//简介内容
	@Column(name = "content",length=16777215)
	private String content;
	
	//地址 address
	@Column(name = "address")
	private String address;
	
	//联系电话 tellphone
	@Column(name = "tellphone")
	private String tellphone;
	
	//电子邮件 email
	@Column(name = "email")
	private String email;
	
	//门户网站 portals
	@Column(name = "portals")
	private String portals;
	
	//公司图片 images
	@Column(name = "images")
	private String images;
	
	//发布时间
	@Column(name = "time")
	private Date time;
	
	//发布者
	@Column(name = "publisher")
	private String publisher;
	
	//发布者id
	@Column(name = "publisherid")
	private String publisherid;
	
	//发布者id（1：发布；0：不发布）
	@Column(name = "status")
	private String status;

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

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getPublisherid() {
		return publisherid;
	}

	public void setPublisherid(String publisherid) {
		this.publisherid = publisherid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTellphone() {
		return tellphone;
	}

	public void setTellphone(String tellphone) {
		this.tellphone = tellphone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPortals() {
		return portals;
	}

	public void setPortals(String portals) {
		this.portals = portals;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}
	
}
