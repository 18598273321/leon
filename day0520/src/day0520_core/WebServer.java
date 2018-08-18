package day0520_core;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import day0520_common.ServletContext;

/**
 * 核心类 用来启动服务
 * 
 * @author 兰
 *
 */

public class WebServer {
	private ServerSocket ss;
	private ExecutorService threadpool;

	public WebServer() {
		try {
			ss = new ServerSocket(ServletContext.port);
			threadpool = Executors.newFixedThreadPool(ServletContext.maxthread);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void start() {
		try {
			while (true) {
				Socket socket = ss.accept();
				threadpool.execute(new ClientHandler(socket));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		WebServer web = new WebServer();
		web.start();
	}

}
