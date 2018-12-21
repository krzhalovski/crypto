package des;

import java.io.FileNotFoundException;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		
		Key key = new Key("133457799BBCDFF1");
		
		ECB ecb = new ECB(key);
		System.out.println(ecb.encrypt("000000000000000000000000000000000000000000000000"));
		System.out.println(ecb.decrypt("948A43F98A834F7E948A43F98A834F7E948A43F98A834F7E"));
		
		CBC cbc = new CBC(key,"0000000000000000");
		System.out.println(cbc.encrypt("000000000000000000000000000000000000000000000000"));
		System.out.println(cbc.decrypt("90C231B301816E0EB20C1235BA406D91D41985DFB4E979C1"));
	}
}
