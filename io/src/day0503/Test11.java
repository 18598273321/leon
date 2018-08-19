package day0503;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Test11 {
	public static void main(String[] args) throws Exception {
		/*
		 * 1 先将保存文件的目录创建列表
		 * 2 然后建立输入流
		 * 3 遍历目录 将文件全部提取出来
		 * 4 新建输出流 将提取的文件全部放在目标文件
		 */
		System.out.println("输入原文件:");
		String from = new Scanner(System.in).nextLine();
		System.out.println("输入目标文件:");
		String to = new Scanner(System.in).nextLine();
		try {
			concat(from,to);
			System.out.println("成功");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("失败");
			// TODO: handle exception
		}
		
		
		}

	private static void concat(String from, String to) throws Exception {
		File file = new File(from);
		File[] files = file.listFiles();
		for (File f : files) {
			FileInputStream in = new FileInputStream(f);
			
			
		}
		
	}
	}


