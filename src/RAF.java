import java.io.*;
import java.util.*;

/* "r" 		Open for reading only. Invoking any of the write methods of the resulting object will cause an IOException to be thrown.
 * "rw" 	Open for reading and writing. If the file does not already exist then an attempt will be made to create it.
 * "rws" 	Open for reading and writing, as with "rw", and also require that every update to the file's content or metadata be written synchronously to the underlying storage device.
 * "rwd"   	Open for reading and writing, as with "rw", and also require that every update to the file's content be written synchronously to the underlying storage device.
 * */

class RAF {
	public static void main(String[] args) throws IOException {
		RandomAccessFile raf = new RandomAccessFile(args[0], "rw");

		System.out.println("Pointer before: " + raf.getFilePointer());
		System.out.println("Length before: " + raf.length()); // value returned in bytes
		raf.writeInt(7);
		raf.writeInt(37);
		raf.writeInt(97);
		raf.writeChars("Gabriel");
		System.out.println("Pointer after: " + raf.getFilePointer());
		System.out.println("Length after: " + raf.length());
		
		raf.seek(0);
		System.out.println(raf.readInt());
		System.out.println(raf.readInt());
		System.out.println(raf.readInt());
		System.out.println(raf.readLine());

		raf.close();

		raf = new RandomAccessFile(args[0], "rw");
		System.out.println(raf.readInt());
		System.out.println(raf.readInt());
		System.out.println(raf.readInt());
		System.out.println(raf.readLine());
		raf.close();
	}
}
