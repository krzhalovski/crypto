package trivium;

import java.io.FileNotFoundException;

public class Main {
	public static void main(String [] args) throws FileNotFoundException {
		
		String key = "00000000000000000000000000000000000000000000000000000000000000000000000010000000";
		String IV =  "00000000000000000000000000000000000000000000000000000000000000000000000000000000";
		
		Converter conv = new Converter();
		
		RotatingState rs = new RotatingState(key,IV);
		System.out.println(conv.binaryToHex(rs.generateNext32Hex()));
		System.out.println(conv.binaryToHex(rs.generateNext32Hex()));
		
		Tester tester = new Tester();
	}
}
