package com.mysoft.xml.parser.dom4j;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.mysoft.xml.bean.Group;
import com.mysoft.xml.bean.Machine;
import com.mysoft.xml.common.Constant;
import com.mysoft.xml.parser.IParser;

public class Dom4JParser implements IParser {

	private SAXReader reader;
	
	public Dom4JParser(){
		reader = new SAXReader();
	}
	
	@Override
	public List<Group> getGroupList(String xmlPath) throws Exception {
		FileInputStream input = null;
		try {
			input = new FileInputStream(new File(xmlPath));
			Document document = reader.read(input);
			Element root = document.getRootElement();
			
			return parse(root);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				if(input != null){
					input.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	private List<Group> parse(Element root) {
		List<Group> groupList = new ArrayList<Group>();
		String xpath = "/" + Constant.ROOT + "/" + Constant.GROUPS + "/" +Constant.GROUP;
		
		List<Element> groupElements = root.selectNodes(xpath);
		if(groupElements != null && groupElements.size() > 0){
			for (int i = 0; i < groupElements.size(); i++) {
				Element groupElement = groupElements.get(i);
				String groupName = groupElement.attributeValue(Constant.NAME);
				Group group = new Group(groupName);
				parseGroup(groupElement, group);
				groupList.add(group);
			}
		}
		return groupList;
	}

	@SuppressWarnings("unchecked")
	private void parseGroup(Element groupElement, Group group) {
		List<Element> machineElementList = groupElement.selectNodes(Constant.MACHINE);
		if (machineElementList != null && machineElementList.size() > 0) {
			for (int i = 0; i < machineElementList.size(); i++) {
				Element machineElement = machineElementList.get(i);  
	            String machineName = machineElement.attributeValue(Constant.NAME);  
	            String machineType = machineElement.attributeValue(Constant.TYPE);  
	            String ip = ((Element) machineElement.selectSingleNode(Constant.IP)).getText();  
	            String hostname = ((Element) machineElement.selectSingleNode(Constant.HOSTNAME)).getText();  
	  
	            Machine machine = new Machine();  
	            machine.setType(machineType);  
	            machine.setIp(ip);  
	            machine.setName(machineName);
	            machine.setHostName(hostname);  
	            group.addMachine(machine); 
			}
		}
	}

}
