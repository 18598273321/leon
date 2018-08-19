package day0503;

import java.io.File;
import java.util.Scanner;

/**
 * file ɾ��Ŀ¼�Ĳ���
 * 
 * @author ��
 *
 */

public class Test3 {
	public static void main(String[] args) {
		System.out.println("������Ŀ¼:");
		String path = new Scanner(System.in).nextLine();
		System.out.println("���Ҫɾ����?�����Իظ���Ӵ!!");
		String y = new Scanner(System.in).nextLine();
		if (!"y".equals(y)) {
			return;
		}
		File file = new File(path);
		boolean bol = delete(file);
		if (bol) {
			System.out.println("ɾ���ɹ�!");
		} else {
			System.out.println("ɾ��ʧ��");
		}

	}

	private static boolean delete(File file) {
		if (file.isFile()) { // ������ļ���ֱ��ɾ��
			return file.delete();
		} // �������Ŀ¼ Ҫ�½�һ��Ŀ¼�б�
		File[] files = file.listFiles();
		// Ȼ���ж�Ŀ¼�Ƿ��ǿ�
		if (files == null) {
			return file.delete();
		}
		// �������Ϊ��.�ͱ���ɾ��
		for (File f : files) {// �ж�f�Ƿ�Ϊ�ļ�
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
