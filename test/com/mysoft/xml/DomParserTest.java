package com.mysoft.xml;

import java.util.List;

import org.junit.Test;

import com.mysoft.xml.bean.Group;
import com.mysoft.xml.parser.IParser;
import com.mysoft.xml.parser.dom.DomParser;
import com.mysoft.xml.parser.dom4j.Dom4JParser;
import com.mysoft.xml.parser.jdom.JDomParser;
import com.mysoft.xml.parser.sax.SaxParser;

public class DomParserTest {

	@Test
	public void testParser(){
		String parserType = "dom4j";
		
		IParser parser = null;
		try {
			if("dom".equals(parserType)){
				parser = new DomParser();
			} else if("sax".equals(parserType)){
				parser = new SaxParser();
			} else if("jdom".equals(parserType)){
				parser = new JDomParser();
			} else if("dom4j".equals(parserType)){
				parser = new Dom4JParser();
			}
			
			List<Group> list = parser.getGroupList("file.xml");
			System.out.println(list);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
