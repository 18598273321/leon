package day0503;

import java.io.File;
import java.util.Scanner;

/**
 * file 删除目录的测试
 * 
 * @author 兰
 *
 */

public class Test3 {
	public static void main(String[] args) {
		System.out.println("请输入目录:");
		String path = new Scanner(System.in).nextLine();
		System.out.println("真的要删除吗?不可以回复的哟!!");
		String y = new Scanner(System.in).nextLine();
		if (!"y".equals(y)) {
			return;
		}
		File file = new File(path);
		boolean bol = delete(file);
		if (bol) {
			System.out.println("删除成功!");
		} else {
			System.out.println("删除失败");
		}

	}

	private static boolean delete(File file) {
		if (file.isFile()) { // 如果是文件就直接删除
			return file.delete();
		} // 否则就是目录 要新建一个目录列表
		File[] files = file.listFiles();
		// 然后判断目录是否是空
		if (files == null) {
			return file.delete();
		}
		// 如果不是为空.就遍历删除
		for (File f : files) {// 判断f是否为文件
			if (f.isFile()) {
				if (!f.delete()) {
					return false;
				} else {
					if (!delete(f)) {
						return false;
					}
				}
			}

		}
		return file.delete();

	}

}
