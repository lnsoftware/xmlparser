package com.mysoft.xml;

import java.util.List;

import org.junit.Test;

import com.mysoft.xml.bean.Group;
import com.mysoft.xml.parser.IParser;
import com.mysoft.xml.parser.dom.DomParser;
import com.mysoft.xml.parser.sax.SaxParser;

public class DomParserTest {

	@Test
	public void testParser(){
		String parserType = "sax";
		
		IParser parser = null;
		try {
			
			if("dom".equals(parserType)){
				parser = new DomParser();
			} else if("sax".equals(parserType)){
				parser = new SaxParser();
			}
			
			List<Group> list = parser.getGroupList("file.xml");
			System.out.println(list);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
