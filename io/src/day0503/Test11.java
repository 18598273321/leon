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
		 * 1 �Ƚ������ļ���Ŀ¼�����б�
		 * 2 Ȼ����������
		 * 3 ����Ŀ¼ ���ļ�ȫ����ȡ����
		 * 4 �½������ ����ȡ���ļ�ȫ������Ŀ���ļ�
		 */
		System.out.println("����ԭ�ļ�:");
		String from = new Scanner(System.in).nextLine();
		System.out.println("����Ŀ���ļ�:");
		String to = new Scanner(System.in).nextLine();
		try {
			concat(from,to);
			System.out.println("�ɹ�");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ʧ��");
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


