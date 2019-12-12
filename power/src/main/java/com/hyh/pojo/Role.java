package com.hyh.pojo;

public class Role {
	private int rid;
	private String rname;
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
	public Role(int rid, String rname) {
		this.rid = rid;
		this.rname = rname;
	}
	public Role() {
	}
	@Override
	public String toString() {
		return "Role [rid=" + rid + ", rname=" + rname + "]";
	}
	

}
