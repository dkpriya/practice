package prac.kp.easy;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ShortToByteArray {

	
	
	public static void main(String[] args){
		//short i = 158;
		short i = 58;
		
		byte[] bytes = new byte[2];
 		bytes[0]=(byte)(i);
  		bytes[1]=(byte)(i>>8);
		System.out.println("["+bytes[0]+","+bytes[1]+"]");

		ByteBuffer bf = ByteBuffer.allocate(2).order(ByteOrder.LITTLE_ENDIAN).putShort(i);
		byte[] ar = bf.array();
		System.out.println("["+ar[0]+","+ar[1]+"]");
		
		//convert it back

		short n = (short) ((bytes[1]<<8) + (bytes[0] & 0xff));
		System.out.println(n);
	}
}
