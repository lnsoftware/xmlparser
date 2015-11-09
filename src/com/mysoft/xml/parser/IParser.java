package com.mysoft.xml.parser;

import java.util.List;

import com.mysoft.xml.bean.Group;

public interface IParser {

	public List<Group> getGroupList(String xmlPath) throws Exception;
	
}
