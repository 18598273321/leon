package day0503;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

/**
 * randomaccessfile  ≤‚ ‘
 * @author ¿º
 *
 */

public class Test4 {
	public static void main(String[] args) throws Exception {
		RandomAccessFile raf = new RandomAccessFile("e:/abc/f3", "rw");
		
		raf.write(97);
		raf.write(98);
		raf.write(98);
		raf.write(356);
		
		byte[] buff={101,102,103,104,105,106,107,108,109,110};
		raf.write(buff);
		raf.write(buff, 4, 3);
		
		
		raf.seek(0);
		int b;
		while((b=raf.read())!=-1) {
			System.out.println(b);
		}
		raf.close();
		
		
	}
	

}
