package des;

import java.io.FileNotFoundException;

public class Main {
	public static void main(String [] args) throws FileNotFoundException {
		Key key = new Key("AABB09182736CCDD");
		DES des = new DES(key);
		
/*		System.out.println(des.s_boxes[1][1][1]);
		System.out.println("");
		
		for(int i = 0;i<16;i++) {
			System.out.println(Converter.booleanToHex(des.roundKeys[i]));
		}
		
		String s = "0000000100100011010001010110011110001001101010111100110111101111";
		System.out.println("");
		System.out.println(s);
		System.out.println(Converter.booleanToHex(des.initialPermutation(s)));*/
		
		String plainText = new String("123456ABCD132536");
		System.out.println(des.encryptText(plainText));
		
	}
}
