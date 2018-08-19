package day0503;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * 拆分文件
 * @author 兰
 *
 */

public class Test8 {
	public static void main(String[] args) {
		/*
		 * 1 先输入要拆分的文件,判断是不是文件,如果不是就返回
		 * 2 输入拆分的大小,注意格式.kb和字节是1024
		 * 3 调用拆分的方法(带上属性)
		 * 4 要新建一个输出的文件 如果存在,就清空.不存在,就新建
		 * 5 新建输入流,单字节读取,新建输出流,
		 * 用bufferedoutputstream 读取单字节效率高
		 * 如果为空,或者满了要再新建输出流
		 */
		System.out.println("输入原文件:");
		String path = new Scanner(System.in).nextLine();
		File file = new File(path);
		if(!file.isFile()) {
			System.out.println("你输入的不是文件,请从新输入;");
			return;
		}
		System.out.println("输入拆分的大小(kb):");
		long size=new Scanner(System.in).nextLong();
		size*=1024;
		try {
			split(file,size);
			System.out.println("成功");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("失败");
			// TODO: handle exception
		}
		
		
	}

	private static void split(File file, long size) throws Exception, Exception {
		/*
		 * 1 先获取文件的名字
		 * 2 创建存放文件的目标目录
		 * 3 创建输入输出流
		 * 4 创建字节计数和拆分文件计数
		 * 5 读取单字节的标准模式
		 * 6 判断输出流是否存在,以及是否满了,如果满了,要关闭,从新新建
		 * 7 新建的输出流文件名字最好新取名字保存
		 */
		String name = file.getName();
		File dir = mubiaomulu(file);
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
		BufferedOutputStream out=null;
		long bytecount=0;
		int filecount=0;
		
		int b;
		while((b=in.read())!=-1) {
			if(out==null||bytecount==size) {
				if(bytecount==size) out.close();
				out=new BufferedOutputStream(new FileOutputStream(new File(dir,name+"."+(++filecount))));
			}
			out.write(b);
			bytecount++;
			
		}
		in.close();
		out.close();
		
	}

	private static File mubiaomulu(File file) {
		/*
		 *  1 新建目标文件 用原文件的地址加上拆分标记
		 *  2 判断是否存在
		 *  3 如果存在 就清空
		 *  4 如果不存在 就新建
		 *  5 返回新建的目标文件
		 */
		File dir = new File(file.getAbsoluteFile()+"_split");
		if(dir.exists()) {
			clear(dir);
		}else {
			dir.mkdirs();
		}
		return dir;
		
	}

	private static void clear(File dir) {
		/*
		 * 1 清理目录
		 * 2 新建列表
		 * 3 如果不能获得值就直接结束
		 * 4 遍历列表
		 * 5 判断是不是文件,是文件就直接删除
		 * 6 不是就 清空了 在删除
		 */
		File[] files = dir.listFiles();
		if(files==null) return;
		for (File f : files) {
			if(f.isFile()) {
				f.delete();
			}else {
				clear(f);
				f.delete();
			}
			
		}
		
	}

	

}
