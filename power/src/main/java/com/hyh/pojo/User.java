package com.hyh.pojo;

import java.util.ArrayList;
import java.util.List;

public class User {
	private int id;
	private String name;
	private String pwd;
	private String sex;
	private String hobby;
	
	private int rid;
	private String rname;
	
	private String rids;
	private List<String> ridList = new ArrayList<String>();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	public String getRids() {
		return rids;
	}
	public void setRids(String rids) {
		this.rids = rids;
	}
	public List<String> getRidList() {
		return ridList;
	}
	public void setRidList(List<String> ridList) {
		this.ridList = ridList;
	}
	public User(int id, String name, String pwd, String sex, String hobby, int rid, String rname, String rids,
			List<String> ridList) {
		this.id = id;
		this.name = name;
		this.pwd = pwd;
		this.sex = sex;
		this.hobby = hobby;
		this.rid = rid;
		this.rname = rname;
		this.rids = rids;
		this.ridList = ridList;
	}
	public User() {
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", pwd=" + pwd + ", sex=" + sex + ", hobby=" + hobby + ", rid="
				+ rid + ", rname=" + rname + ", rids=" + rids + ", ridList=" + ridList + "]";
	}
	
	
	

}
