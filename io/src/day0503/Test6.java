package day0503;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

/**
 * 测试图片的分辨率和求反
 * 
 * @author 兰
 *
 */

public class Test6 {
	public static void main(String[] args) throws Exception {
		RandomAccessFile raf = new RandomAccessFile("e:/abc/aaa.bmp", "rw");
		raf.seek(18);
		int x = 0;
		int y = 0;

		x |= raf.read() << 0;
		x |= raf.read() << 8;
		x |= raf.read() << 16;
		x |= raf.read() << 32;
		y |= raf.read() << 0;
		y |= raf.read() << 8;
		y |= raf.read() << 16;
		y |= raf.read() << 32;
		
		System.out.println(x);
		System.out.println(y);
		
		raf.seek(54);
		byte[] buff=new byte[8192];
		int n;
		while((n=raf.read(buff))!=-1) {
			for(int i=0;i<n;i++) {
				buff[i]=(byte)(~buff[i]);
			}
			raf.seek(raf.getFilePointer()-n);
			raf.write(buff, 0, n);
		}
		raf.close();

	}

}
