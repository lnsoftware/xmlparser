package com.mysoft.xml.parser.sax;

import java.util.List;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.mysoft.xml.bean.Group;
import com.mysoft.xml.parser.IParser;

public class SaxParser implements IParser {

	private XMLReader xmlReader;
	
	public SaxParser(){
		try {
			xmlReader = XMLReaderFactory.createXMLReader();
		} catch (SAXException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Group> getGroupList(String xmlPath) throws Exception {
		ResourceHandler handler = new ResourceHandler();
		xmlReader.setContentHandler(handler);
		xmlReader.parse(xmlPath);
		
		return handler.getGroupList();
	}

}
