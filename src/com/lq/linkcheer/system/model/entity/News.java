package com.lq.linkcheer.system.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/** 新闻 */
@Entity
@Table(name = "t_common_news")
public class News implements Serializable {

	private static final long serialVersionUID = 1L;

	
	//新闻ID
	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@GeneratedValue(generator = "uuid")
	private String id;
	
	
	//新闻编号
	@Column(name = "news_id")
	private String news_id;
	
	
	//新闻标题
	@Column(name = "title")
	private String title;
	
	//新闻内容
	@Column(name = "content", length=25536)
	private String content;
	
	//新闻简介
	@Column(name = "summary")
	private String summary;
	
	//发布时间
	@Column(name = "time")
	private Date time;
	
	//发布者
	@Column(name = "publisher")
	private String publisher;
	
	//发布者id
	@Column(name = "publisherid")
	private String publisherid;
	
	//时间段
	@Column(name = "news_source")
	private String news_source;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNews_id() {
		return news_id;
	}

	public void setNews_id(String news_id) {
		this.news_id = news_id;
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

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
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

	public String getNews_source() {
		return news_source;
	}

	public void setNews_source(String news_source) {
		this.news_source = news_source;
	}

	public String getPublisherid() {
		return publisherid;
	}

	public void setPublisherid(String publisherid) {
		this.publisherid = publisherid;
	}
	
}
