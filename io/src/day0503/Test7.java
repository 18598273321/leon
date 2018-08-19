package day0503;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.Scanner;

/**
 * 文件的加密解密
 * 
 * @author 兰
 *
 */

public class Test7 {
	public static void main(String[] args) {
		System.out.println("请输入文件:");
		String path = new Scanner(System.in).nextLine();
		File file = new File(path);
		if (!file.isFile()) {
			System.out.println("输入的不是文件.");
		}
		System.out.println("key: ");
		int key = new Scanner(System.in).nextInt();
		try {
			encript(file, key);
			System.out.println("成功");
		} catch (Exception e) {
			System.out.println("失败");

		}
	}

	private static void encript(File file, int key) throws Exception {
		RandomAccessFile raf = new RandomAccessFile(file, "rw");

		byte[] buff = new byte[8192];
		int n;
		while ((n = raf.read(buff)) != -1) {
			for (int i = 0; i < n; i++) {
				buff[i] ^= buff[i];
			}
			raf.seek(raf.getFilePointer() - n);
			raf.write(buff, 0, n);
		}
		raf.close();

	}

}
