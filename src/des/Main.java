package des;

import java.io.FileNotFoundException;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		
		ECB ecb = new ECB(key);
		System.out.println(ecb.encrypt("000000000000000000000000000000000000000000000000"));
		System.out.println(ecb.decrypt("948A43F98A834F7E948A43F98A834F7E948A43F98A834F7E"));

	}
}
