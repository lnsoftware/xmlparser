package com.mysoft.xml.bean;

/**
 * Machine的属性定义
 */
public class Machine {
	
	private String ip;
	
	private String hostName;
	
	private String name;
	
	private String type;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Machine [ip=" + ip + ", hostName=" + hostName + ", name="
				+ name + ", type=" + type + "]";
	}
	
}
