package com.hyh.pojo;

public class Module {
	private int mid;
	private String moduleName;
	private String url;
	private int pid;
	private boolean open;
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public Module(int mid, String moduleName, String url, int pid, boolean open) {
		this.mid = mid;
		this.moduleName = moduleName;
		this.url = url;
		this.pid = pid;
		this.open = open;
	}
	public Module() {
	}
	@Override
	public String toString() {
		return "Module [mid=" + mid + ", moduleName=" + moduleName + ", url=" + url + ", pid=" + pid + ", open=" + open
				+ "]";
	}
	
	
}
