package day0520_core;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import day0520_common.HttpContext;
import day0520_common.ServletContext;
import day0520_http.HttpRequest;
import day0520_http.HttpResponse;

/**
 * 这个类是线程类 用来处理客户端的请求 1 实现runnable接口 2 申明socket对象 代表客户端 3 重写run方法 4 测试
 */

public class ClientHandler implements Runnable {
	private Socket socket;

	public ClientHandler(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		PrintStream ps;
		System.out.println("pk");
		try {
			HttpRequest request = new HttpRequest(socket.getInputStream());
			HttpResponse response=new HttpResponse(socket.getOutputStream());
			File file = new File(ServletContext.webroot + request.getUri());
			if(!file.exists()){
				file=new File(ServletContext.webroot+"/"+ServletContext.notfoundpage);
				response.setStatus(404);
			}else{
				response.setStatus(200);
				
			}
			if(request.getUri().startsWith("/RegistUser")||
			request.getUri().startsWith("/LoginUser")){
				service(request,response);
				return ;
				
			}
			
			
			response.setProtocol(ServletContext.protocol);
			//response.setStatus(200);
			//response.setContentType("text/html");
			response.setContentType(getcontentbyfileext(file));
			response.setContentLength((int)file.length());

			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
			byte[] bs = new byte[(int) file.length()];
			bis.read(bs);
			OutputStream stream = response.getOutputstream();
			stream.write(bs);
			stream.flush();
			socket.close();

		} catch (

		IOException e)

		{
			e.printStackTrace();
		}

	}

	private void service(HttpRequest request, HttpResponse response) {
		if(request.getUri().startsWith("/RegistUser")){
		
		//注册
		//1 注册驱动 2连接服务器 3获取传输器
		//4 执行sql 5解析结果集 6关闭
		Connection conn=null;
		PreparedStatement ps=null;
		
		try {
			conn=JDBCUtils.getconnection();
			String sql="insert into user values(null,?,?)";
			ps=conn.prepareStatement(sql);
			String username = request.getparam("username");
			String password = request.getparam("password");
			ps.setString(1, username);
			ps.setString(2, password);
			ps.executeUpdate();
			/*
			 * 当用户注册完成时 响应注册成功页面
			 */
			response.setProtocol("HTTP/1.1");
			response.setStatus(HttpContext.CODE_OK);
			//注册成功页面
			File file = new File(ServletContext.webroot+"/"+"reg_success.html");
			response.setContentType(getcontentbyfileext(file));
			response.setContentLength((int)file.length());
			//读取页面文件
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
			byte[] bs=new byte[(int)file.length()];
			bis.read(bs);
			response.getOutputstream().write(bs);
			response.getOutputstream().flush();
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCUtils.close(conn, ps, null);
		}
		}else{
			//登陆
			Connection conn=null;
			PreparedStatement ps=null;
			ResultSet rs=null;
			
			
			try {
				conn=JDBCUtils.getconnection();
				String sql="select * from user where username=? and password=?";
				ps=conn.prepareStatement(sql);
				String username=request.getparam("username");
				String password=request.getparam("password");
				ps.setString(1, username);
				ps.setString(2, password);
				rs=ps.executeQuery();
				if(rs.next()){
					response.setProtocol("HTTP/1.1");
					response.setStatus(HttpContext.CODE_OK);
					File file=new File(ServletContext.webroot+"/"+"log_success.html");
					response.setContentType(getcontentbyfileext(file));
					response.setContentLength((int) file.length());
					BufferedInputStream bis=new BufferedInputStream(new FileInputStream(file));
					byte[] bs=new byte[(int)file.length()];
					bis.read(bs);
					response.getOutputstream().write(bs);
					response.getOutputstream().flush();
					socket.close();
					
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
	}

	private String getcontentbyfileext(File file) {
		// map  html,text/html
		//localhost:8080/index.html
		String name = file.getName();
		String ext = name.substring(name.lastIndexOf(".")+1);
		String type = ServletContext.map.get(ext);
		return type;
		
	}

}
