package com.mysoft.xml.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Group组对Machine的操作
 */
public class Group {

	private String name;  
	
    private List<Machine> machineList;
    
    public Group(){}
    
    public Group(String gname){
    	this.name = gname;
    	machineList = new ArrayList<Machine>();
    }
    
    /**
     * 添加机器
     */
    public void addMachine(Machine machine){
    	machineList.add(machine);
    }
    
    /**
     * 删除机器
     */
    public void removeMachine(Machine machine){
    	machineList.remove(machine);
    }
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Machine> getMachineList() {
		return machineList;
	}

	public void setMachineList(List<Machine> machineList) {
		this.machineList = machineList;
	}

	@Override
	public String toString() {
		return "Group [name=" + name + ", machineList=" + machineList + "]";
	}  
    
}
