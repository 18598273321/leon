package day0503;

import java.io.File;
import java.util.Scanner;

/**
 * ��Ŀ¼���ֽ�ֵ��С
 * 
 * @author ��
 *
 */

public class Test2 {
	public static void main(String[] args) {
		System.out.println("������Ŀ¼");
		String path = new Scanner(System.in).nextLine();
		File file = new File(path);// �Ƚ������Ŀ¼������file����
		// ���ж�file�����ǲ���Ŀ¼
		if (!file.isDirectory()) {
			System.out.println("����Ĳ���Ŀ¼!!");
			return;
		} // ����һ����������file���ܴ�С
		long size = filelength(file);
		System.out.println(size);

	}

	private static long filelength(File file) {
		// ���½�Ŀ¼�б���
		File[] f = file.listFiles();
		// ���ж��б��Ƿ����,�����޷�����,�᷵��nullֵ
		if (f == null)
			return 0;
		// ����һ������.�ۼ�ֵ
		long sum = 0;
		// ��������
		for (File f1 : f) {// ���ж��ǲ����ļ�,���ļ���ֱ�Ӽ�
			if (f1.isFile()) {
				sum += f1.length();
			} else {// �����ļ�
				sum += filelength(f1); // �ݹ��Լ�

			}

		}
		return sum;

	}

}
