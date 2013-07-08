package com.tsoj.web.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="Solutions")
public class Solution {
	@Id
	@Column(name="sid", nullable=false, unique=true)
	@GenericGenerator(name="generator", strategy="identity")
	@GeneratedValue(generator="generator")
	private int sid;
	@Column
	private String uid;
	@Column
	private int pid;
	@Column
	private String slang;
	@Column
	private String scode;
	@Column
	private Date ssbmttime;
	@Column
	private Date stesttime;
	@Column
	private int stime;
	@Column
	private int smemory;
	@Column
	private String stester;
	@Column
	private String sresult;
	
	public void setSid(int sid) { this.sid = sid; }
	public int getSid() { return this.sid; }
	public void setUid(String uid) { this.uid = uid; }
	public String getUid() { return this.uid; }
	public void setPid(int pid) { this.pid = pid; }
	public int getPid() { return this.pid; }
	public void setSlang(String slang) { this.slang = slang; }
	public String getSlang() { return this.slang; }
	public void setScode(String scode) { this.scode = scode; }
	public String getScode() { return this.scode; }
	public void setSsbmttime(Date ssbmttime) { this.ssbmttime = ssbmttime; }
	public Date getSsbmttime() { return this.ssbmttime; }
	public void setStesttime(Date stesttime) { this.stesttime = stesttime; }
	public Date getStesttime() { return this.stesttime; }
	public void setStime(int stime) { this.stime = stime; }
	public int getStime() { return this.stime; }
	public void setSmemory(int smemory) { this.smemory = smemory; }
	public int getSmemory() { return this.smemory; }
	public void setStester(String stester) { this.stester = stester; }
	public String getStester() { return this.stester; }
	public void setSresult(String sresult) { this.sresult = sresult; }
	public String getSresult() { return this.sresult; }
	
}
