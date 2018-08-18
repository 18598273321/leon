package day0520_http;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import day0520_common.HttpContext;

/**
 * 这个类是用来封装相应信息的类
 * 1 声明相应的参数  并get /set
 * 2 在构造函数中 传入outputstream对象 并保存在类中
 * 3 改造getoutputstream方法  保证响应头只被发送一次
 * 4 改造run方法  将相应代码进行修改
 * 5 测试
 * 
 * @author 兰
 *
 */

public class HttpResponse {
	private String protocol;//相应遵顼的规则
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
