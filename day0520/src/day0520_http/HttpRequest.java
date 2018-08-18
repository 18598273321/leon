package day0520_http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * �������������װ������Ϣ����
 * 1 ��������Ĳ��� ��get/set
 * 2 �ڹ��캯���г�ʼ��
 * @author ��
 *
 */

public class HttpRequest {
	private String method;//����ʽ
	private String uri;
	private String protocol;
	private Map<String,String> parammap=new HashMap<String,String>();
	
	
	public HttpRequest(InputStream in){
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		;
		try {
			 String line = reader.readLine();
			if(line!=null&&line.length()>0){
				String[] datas = line.split(" ");
				method=datas[0];
				uri=datas[1];
				protocol=datas[2];
				if(uri.equals("/")){
					uri="/index.html";
				}
				//LoginUser?username=add&&password=123
				if(uri!=null&&uri.contains("?")){
					String str = uri.split("\\?")[1];
					//(username=add&&password=123)
					String[] strs = str.split("&");
					//[username=add,password=123]
					for (String s : strs) {
						String key = s.split("=")[0];
						String value = s.split("=")[1];
						parammap.put(key, value);
						
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			
		}
	}
	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public String getparam(String key){
		String value = parammap.get(key);
		return value;
		
	}


}
