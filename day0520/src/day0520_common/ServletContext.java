package day0520_common;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 这个类是用来封装服务器相关参数的类
 * 1 声明4个服务器相关的参数
 * 2 在静态代码块中初始化参数值
 * 3 改造webserver(端口号 线程池)
 * 4 改造clienthandler(遵循的协议 资源的根目录)
 */

public class ServletContext {
	public static int port;
	public static int maxthread;
	public static String protocol;
	public static String webroot;
	public static String notfoundpage;//404
	public static Map<String, String> map=new HashMap<String, String>();
	
	
	static{
		init();//解析xml并给参数赋值
	}

	private static void init() {
		try {
			SAXReader reader = new SAXReader();
			File file = new File("config/web.xml");
			 Document doc = reader.read(file);
			//开始读取文件
			Element rootelement = doc.getRootElement();
			Element serviceelement = rootelement.element("service");
			Element connele = serviceelement.element("connector");
			port=Integer.valueOf(connele.attributeValue("port"));
			maxthread=Integer.valueOf(connele.attributeValue("maxThread"));
			protocol=connele.attributeValue("protocol");
			webroot=serviceelement.elementText("webroot");
			notfoundpage=serviceelement.elementText("not-found-page");
			
			//初始化typemap
			/*
			 * 1 获取ext的值作为key
			 * 2 type值做为value存起来
			 * 3 要访问资源时 可以根据后缀名从map中取对应的type
  			 */
			 List<Element> types = rootelement.element("type-mappings").elements();
			 for (Element e : types) {
				 String key = e.attributeValue("ext");
				 String value = e.attributeValue("type");
				 map.put(key, value);
				
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
		
	}
	

}
