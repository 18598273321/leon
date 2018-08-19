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
 * ����ļ�
 * @author ��
 *
 */

public class Test8 {
	public static void main(String[] args) {
		/*
		 * 1 ������Ҫ��ֵ��ļ�,�ж��ǲ����ļ�,������Ǿͷ���
		 * 2 �����ֵĴ�С,ע���ʽ.kb���ֽ���1024
		 * 3 ���ò�ֵķ���(��������)
		 * 4 Ҫ�½�һ��������ļ� �������,�����.������,���½�
		 * 5 �½�������,���ֽڶ�ȡ,�½������,
		 * ��bufferedoutputstream ��ȡ���ֽ�Ч�ʸ�
		 * ���Ϊ��,��������Ҫ���½������
		 */
		System.out.println("����ԭ�ļ�:");
		String path = new Scanner(System.in).nextLine();
		File file = new File(path);
		if(!file.isFile()) {
			System.out.println("������Ĳ����ļ�,���������;");
			return;
		}
		System.out.println("�����ֵĴ�С(kb):");
		long size=new Scanner(System.in).nextLong();
		size*=1024;
		try {
			split(file,size);
			System.out.println("�ɹ�");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ʧ��");
			// TODO: handle exception
		}
		
		
	}

	private static void split(File file, long size) throws Exception, Exception {
		/*
		 * 1 �Ȼ�ȡ�ļ�������
		 * 2 ��������ļ���Ŀ��Ŀ¼
		 * 3 �������������
		 * 4 �����ֽڼ����Ͳ���ļ�����
		 * 5 ��ȡ���ֽڵı�׼ģʽ
		 * 6 �ж�������Ƿ����,�Լ��Ƿ�����,�������,Ҫ�ر�,�����½�
		 * 7 �½���������ļ����������ȡ���ֱ���
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
		 *  1 �½�Ŀ���ļ� ��ԭ�ļ��ĵ�ַ���ϲ�ֱ��
		 *  2 �ж��Ƿ����
		 *  3 ������� �����
		 *  4 ��������� ���½�
		 *  5 �����½���Ŀ���ļ�
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
		 * 1 ����Ŀ¼
		 * 2 �½��б�
		 * 3 ������ܻ��ֵ��ֱ�ӽ���
		 * 4 �����б�
		 * 5 �ж��ǲ����ļ�,���ļ���ֱ��ɾ��
		 * 6 ���Ǿ� ����� ��ɾ��
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
