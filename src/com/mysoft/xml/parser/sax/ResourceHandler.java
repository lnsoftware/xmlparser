package com.mysoft.xml.parser.sax;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.mysoft.xml.bean.Group;
import com.mysoft.xml.bean.Machine;
import com.mysoft.xml.common.Constant;

public class ResourceHandler extends DefaultHandler {

	private List<Group> groupList = null;
	
	/**
	 * <group>节点开始时压栈, 结束时出栈 
	 */
	private Stack<Group> groupStack = new Stack<Group>();
	
	/**
	 * <machine>节点开始时压栈, 结束时出栈 
	 */
	private Stack<Machine> machineStack = new Stack<Machine>();
	
	/**
	 * 对<ip>和<hostname>这样的节点，因为是节点+文本类型，
	 * 因此需要用这个标示节点的内容 
	 */
	private String currentNodeText;
	
	/**
	 * xml节点开始时的处理方法
	 * -------------------
	 * 主要是遇到<group>和<machine>节点的开始标签时,
	 * 需要把group和group入栈
	 */
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if(Constant.GROUPS.equals(qName)){
			// 初始化groupList集合
			groupList = new ArrayList<Group>();
			
		} else if(Constant.GROUP.equals(qName)){
			String groupName = attributes.getValue(Constant.NAME);
			Group group = new Group(groupName);
			// 将group压栈操作
			groupStack.push(group);
			
		} else if(Constant.MACHINE.equals(qName)){
			String machineName = attributes.getValue(Constant.NAME);
			String machineType = attributes.getValue(Constant.TYPE);
			
			Machine m = new Machine();
			m.setName(machineName);
			m.setType(machineType);
			// 将machine压栈操作
			machineStack.push(m);
			
		}
	}
	
	/**
	 * XML节点直接加文本时的处理
	 * --------------------
	 * 主要是处理<ip>和<name>节点的值
	 */
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		currentNodeText = new String(ch, start, length);
	}
	
	/**
	 * xml节点结束时的处理方法
	 * -------------------
	 * 1. 遇到<group>和<machine>的结束节点时,需要把
	 *    group和machine出栈保存
	 * 2. 遇到ip和hostname节点结束标签时,保存当前文本
	 * 	  为相应的属性值，并清空当前文本变量   
	 */
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if(Constant.GROUP.equals(qName)){
			// 向groupList中添加出栈的group对象
			groupList.add(groupStack.pop());
			
		} else if(Constant.MACHINE.equals(qName)){
			// 向group栈顶中添加出栈的machine对象
			groupStack.peek().addMachine(machineStack.pop());
			
		} else if(Constant.IP.equals(qName)){
			// 向machine栈顶元素设置ip
			machineStack.peek().setIp(currentNodeText);
			currentNodeText = null;
			
		} else if(Constant.HOSTNAME.equals(qName)){
			// 向machine栈顶元素设置hostname
			machineStack.peek().setHostName(currentNodeText);
			currentNodeText = null;
		}
	}
	
	public List<Group> getGroupList() {
		return groupList;
	}

}
