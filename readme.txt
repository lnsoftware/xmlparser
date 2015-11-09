****************************
		XML解析教程
****************************
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
	2.2 解析说明
		
		
