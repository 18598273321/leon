package day0503;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.Arrays;

public class Test5 {
	public static void main(String[] args) throws Exception {
		RandomAccessFile raf = new RandomAccessFile("e:/abc/f3", "rw");
		
		//批量读取的标准模式
		byte[] buff=new byte[5];
		int n;
		while((n=raf.read(buff))!=-1) {
			System.out.println(n+": "+ Arrays.toString(buff));
		}
		raf.close();
	}

}
