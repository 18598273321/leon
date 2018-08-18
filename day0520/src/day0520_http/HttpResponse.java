package day0520_http;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import day0520_common.HttpContext;

/**
 * �������������װ��Ӧ��Ϣ����
 * 1 ������Ӧ�Ĳ���  ��get /set
 * 2 �ڹ��캯���� ����outputstream���� ������������
 * 3 ����getoutputstream����  ��֤��Ӧͷֻ������һ��
 * 4 ����run����  ����Ӧ��������޸�
 * 5 ����
 * 
 * @author ��
 *
 */

public class HttpResponse {
	private String protocol;//��Ӧ����Ĺ���
	private int status;
	private String contentType;
	private int contentLength;
	private Map<Integer,String> descmap=new HashMap<Integer,String>();
	
	private OutputStream outputstream;
	
	public HttpResponse(OutputStream outputstream){
		descmap.put(HttpContext.CODE_OK, HttpContext.DESC_OK);
		descmap.put(HttpContext.CODE_NOTFOUND, HttpContext.DESC_NOTFOUND);
		descmap.put(HttpContext.CODE_ERROR, HttpContext.DESC_ERROR);
		this.outputstream=outputstream;
		
	}
	private boolean issend;
	public OutputStream getOutputstream() {
		if(!issend){
			PrintStream ps=new PrintStream(outputstream);
			ps.println(protocol+" "+status+" "+descmap.get(status));
			ps.println("Content-Type:"+contentType);
			ps.println("Content-Length:"+contentLength);
			ps.println();
			issend=true;
		}
		return outputstream;
		
	}
	
	
	

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public int getContentLength() {
		return contentLength;
	}

	public void setContentLength(int contentLength) {
		this.contentLength = contentLength;
	}

	

	public void setOutputstream(OutputStream outputstream) {
		this.outputstream = outputstream;
	}
	

}
