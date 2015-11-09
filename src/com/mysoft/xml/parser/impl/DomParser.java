package com.mysoft.xml.parser.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.mysoft.xml.bean.Group;
import com.mysoft.xml.bean.Machine;
import com.mysoft.xml.common.Constant;
import com.mysoft.xml.parser.IParser;

public class DomParser implements IParser {

	// 定义DocumentBuilder对象
	private DocumentBuilder builder;
	
	public DomParser() {
		try {
			// 实例化DocumentBuilder对象
			builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Group> getGroupList(String xmlPath) throws SAXException, IOException {
		
		Document doc = builder.parse(xmlPath);
		
		// 查找所有的group节点
		NodeList groupList = doc.getElementsByTagName(Constant.GROUP);
		
		// 获取节点个数
		int groupLength = groupList.getLength();
		if(groupLength <= 0){
			return null;
		}
		
		// group集合
		List<Group> list = new ArrayList<Group>();
		for (int i = 0; i < groupLength; i++) {
			// 获取group节点的属性
			NamedNodeMap groupAttributes = groupList.item(i).getAttributes();
			// 获取group name属性
			String groupName = groupAttributes.getNamedItem(Constant.NAME).getNodeValue();
		
			Group group = new Group(groupName);
			// 解析当前group下面的machine
			parseMachinesOfGroup(groupList.item(i), group);
			
			list.add(group);
		}
		return list;
	}

	/**
	 * 解析当前group下面的machine
	 */
	private void parseMachinesOfGroup(Node node, Group group) {
		// 获取所有的machine节点
		NodeList machineNodes = node.getChildNodes();
		if(machineNodes == null || machineNodes.getLength() <= 0){
			return;
		}
		// 遍历所有的machine
		for (int i = 0; i < machineNodes.getLength(); i++) {
			Node machineNode = machineNodes.item(i);
			// 必要的检查
			if(machineNode.getNodeName() == null || !machineNode.getNodeName().equals(Constant.MACHINE)){
				continue;
			}
			// machine节点的属性
			NamedNodeMap machineAttr = machineNode.getAttributes();
			// machine name
			String machineName = machineAttr.getNamedItem(Constant.NAME).getNodeValue();
			// machine type
			String machineType = machineAttr.getNamedItem(Constant.TYPE).getNodeValue();
			
			Machine machine = new Machine();
			machine.setName(machineName);
			machine.setType(machineType);
			
			// 遍历每一个machine节点下面的属性
			NodeList machineChildren = machineNode.getChildNodes();
			int machineChildrenLen = machineChildren.getLength();
			for (int j = 0; j < machineChildrenLen; j++) {
				Node machineChild = machineChildren.item(j);
				if(machineChild.getNodeName().equals(Constant.IP)){
					String ip = machineChild.getTextContent();
					machine.setIp(ip);
				} else if(machineChild.getNodeName().equals(Constant.HOSTNAME)){
					String hostName = machineChild.getTextContent();
					machine.setHostName(hostName);
				}
			}
			group.addMachine(machine);
		}
	}

}
