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
 * �������������װ��������ز�������
 * 1 ����4����������صĲ���
 * 2 �ھ�̬������г�ʼ������ֵ
 * 3 ����webserver(�˿ں� �̳߳�)
 * 4 ����clienthandler(��ѭ��Э�� ��Դ�ĸ�Ŀ¼)
 */

public class ServletContext {
	public static int port;
	public static int maxthread;
	public static String protocol;
	public static String webroot;
	public static String notfoundpage;//404
	public static Map<String, String> map=new HashMap<String, String>();
	
	
	static{
		init();//����xml����������ֵ
	}

	private static void init() {
		try {
			SAXReader reader = new SAXReader();
			File file = new File("config/web.xml");
			 Document doc = reader.read(file);
			//��ʼ��ȡ�ļ�
			Element rootelement = doc.getRootElement();
			Element serviceelement = rootelement.element("service");
			Element connele = serviceelement.element("connector");
			port=Integer.valueOf(connele.attributeValue("port"));
			maxthread=Integer.valueOf(connele.attributeValue("maxThread"));
			protocol=connele.attributeValue("protocol");
			webroot=serviceelement.elementText("webroot");
			notfoundpage=serviceelement.elementText("not-found-page");
			
			//��ʼ��typemap
			/*
			 * 1 ��ȡext��ֵ��Ϊkey
			 * 2 typeֵ��Ϊvalue������
			 * 3 Ҫ������Դʱ ���Ը��ݺ�׺����map��ȡ��Ӧ��type
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
