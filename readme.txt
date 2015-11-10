****************************
		XML解析教程
****************************
参考：http://qingkangxu.iteye.com/blog/1838405

解析XML的Java框架有很多
1. DOM
2. SAX
3. Dom4j
4. JDOM
5. STAX
等技术

1. DOM解析
	1.1 使用jar包
		org.w3c.dom(系统自带)
	1.2 解析说明
		DOM解析比较占用内存，因为DOM需要一次性加载整个文件内容到内存,<解析大文件时不建议使用>, DOM解析不需要引入
		额外的jar包,只需要jdk即可，比较轻量级
		结合DocumentBuilder 和 DocumentBuilderFacoty使用

2. SAX解析
	2.1 使用jar包
		org.xml.sax(系统自带)
	2.2 解析说明
		SAX解析是基于事件的解析，不会加载整个xml文件到内存中，按需读取，一般使用SAX解析时都会借助于栈和数据模型
		的衔接
		
3. JDOM解析
	3.1 使用jar包
		org.jdom包和jaxen(xpath)包
	3.2 解析说明
		主要借助于xpath完成解析
					
		
