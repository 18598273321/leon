package day0503;
/**
 * 测试file 基本的创建和属性  目录列表
 */

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Test1 {
	public static void main(String[] args) throws IOException {
		File file = new File("e:/abc/aa/bb/cc/");
		file.mkdirs();
		File file2 = new File("e:/abc/f2");
		file2.createNewFile();
		File file1 = new File(file, "f1");
		file1.createNewFile();

		System.out.println(file1.length());
		System.out.println(file1.getName());
		System.out.println(file1.exists());
		System.out.println(file1.lastModified());
		System.out.println(file1.getParentFile());
		System.out.println(file1.isFile());
		System.out.println(file1.isDirectory());

		System.out.println("请输入目录");
		String path = new Scanner(System.in).nextLine();
		File file3 = new File(path);
		if (!file3.isDirectory()) {
			System.out.println("输入的不是目录,请从新输入");
		}

		String[] list = file3.list();
		for (String f1 : list) {
			System.out.println(f1);
		}
		File[] files = file3.listFiles();
		for (File f2 : files) {
			System.out.println(f2.getName() + ":  " + f2.length());

		}

	}

}
