package com.tsoj.web.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="Comments")
public class Comment {
	@Id
	@Column(name="cmid", nullable=false, unique=true)
	@GenericGenerator(name="generator", strategy="identity")
	@GeneratedValue(generator="generator")
	private int cmid;
	//@ManyToOne(cascade={CascadeType.ALL})
	//@JoinColumn(name="uid", nullable=false, updatable=false, insertable=false)
	@Column
	private String uid;
	//@ManyToOne(cascade={CascadeType.ALL})
	//@JoinColumn(name="pid", nullable=false, updatable=false, insertable=false)
	@Column
	private int pid;
	@Column
	private String cmcontent;
	@Column
	private Date cmtime;
	
	public void setCmid(int cmid) { this.cmid = cmid; }
	public int getCmid() { return this.cmid; }
	public void setUid(String uid) { this.uid = uid; }
	public String getUid() { return this.uid; }
	public void setPid(int pid) { this.pid = pid; }
	public int getPid() { return this.pid; }
	public void setCmcontent(String cmcontent) { this.cmcontent = cmcontent; }
	public String getCmcontent() { return this.cmcontent; }
	public void setCmtime(Date cmtime) { this.cmtime = cmtime; }
	public Date getCmtime() { return this.cmtime; }
	
}
