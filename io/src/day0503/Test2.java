package day0503;

import java.io.File;
import java.util.Scanner;

/**
 * 求目录的字节值大小
 * 
 * @author 兰
 *
 */

public class Test2 {
	public static void main(String[] args) {
		System.out.println("请输入目录");
		String path = new Scanner(System.in).nextLine();
		File file = new File(path);// 先将输入的目录创建成file对象
		// 先判断file对象是不是目录
		if (!file.isDirectory()) {
			System.out.println("输入的不是目录!!");
			return;
		} // 调用一个方法来求file的总大小
		long size = filelength(file);
		System.out.println(size);

	}

	private static long filelength(File file) {
		// 先新建目录列表里
		File[] f = file.listFiles();
		// 再判断列表是否存在,否则无法进入,会返回null值
		if (f == null)
			return 0;
		// 创建一个变量.累加值
		long sum = 0;
		// 遍历数组
		for (File f1 : f) {// 先判断是不是文件,是文件就直接加
			if (f1.isFile()) {
				sum += f1.length();
			} else {// 不是文件
				sum += filelength(f1); // 递归自己

			}

		}
		return sum;

	}

}
