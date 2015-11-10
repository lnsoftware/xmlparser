package com.mysoft.xml.parser.jdom;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.xpath.XPath;

import com.mysoft.xml.bean.Group;
import com.mysoft.xml.bean.Machine;
import com.mysoft.xml.common.Constant;
import com.mysoft.xml.parser.IParser;

public class JDomParser implements IParser {

	private SAXBuilder builder;
	
	public JDomParser() {
		builder = new SAXBuilder();
	}
	
	@Override
	public List<Group> getGroupList(String xmlPath) throws Exception {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(new File(xmlPath));
			Document document = builder.build(fis);
			Element root = document.getRootElement();
//			List<Element> groupList = XPath.selectNodes(root, "/"
//					+ Constant.ROOT + "/" + Constant.GROUPS + "/"
//					+ Constant.GROUP + "[name=wlanAppGroup]");
		
			return parse(root);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
			} catch (Exception e) {
			}
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	private List<Group> parse(Element root) throws Exception {
		List<Group> groupList = new ArrayList<Group>();
		// <group> 节点是从根节点开始select, 一次xpath需要使用绝对路径(/开头)
		String xpath = "/" + Constant.ROOT + "/" + Constant.GROUPS + "/" + Constant.GROUP;  
		// group有多个节点，所以使用XPath.selectNodes(root, xpath)
		List<Element> groupElementList = XPath.selectNodes(root, xpath); 
		for (int i = 0; i < groupElementList.size(); i++) {
			Element groupElement = groupElementList.get(i);
			
			String groupName = groupElement.getAttributeValue(Constant.NAME);
			Group group = new Group(groupName);
			// 解析group
			parseGroup(groupElement, group);
			
			groupList.add(group);
		}
		return groupList;
	}

	/**
	 * 解析group元素
	 * @throws JDOMException 
	 */
	@SuppressWarnings("unchecked")
	private void parseGroup(Element groupElement, Group group)
			throws JDOMException {
		// 一个group下有多个machine节点，所有使用XPath.selectNodes(object, xpath)
		List<Element> machineElements = XPath.selectNodes(groupElement, Constant.MACHINE);

		for (int i = 0; i < machineElements.size(); i++) {
			Element machineElement = machineElements.get(i);
			String machineName = machineElement.getAttributeValue(Constant.NAME);
			String machineType = machineElement.getAttributeValue(Constant.TYPE);
			String ip = ((Element) XPath.selectSingleNode(machineElement, Constant.IP)).getText();
			String hostname = ((Element) XPath.selectSingleNode(machineElement, Constant.HOSTNAME)).getText();

			Machine machine = new Machine();
			machine.setType(machineType);
			machine.setIp(ip);
			machine.setName(machineName);
			machine.setHostName(hostname);
			
			group.addMachine(machine);
		}
	}
}

