package com.mysoft.xml;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.junit.Test;
import org.xml.sax.SAXException;

import com.mysoft.xml.bean.Group;
import com.mysoft.xml.parser.impl.DomParser;

public class DomParserTest {

	@Test
	public void testDomParser(){
		
		try {
			DomParser dp = new DomParser();
			List<Group> groupList = dp.getGroupList("file.xml");
			System.out.println(groupList);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
