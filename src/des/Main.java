package des;

import java.io.FileNotFoundException;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		CutAndPaste cap = new CutAndPaste();
		Key key = new Key("133457799BBCDFF1");
		ECB ecb = new ECB(key);
		
		String msg1 = "Trajko mnogu ja saka Petranka";
		String msg2 = "Mihail mnogu ja saka Petranka"; 
		
		String msg1H = cap.asciiToHex(msg1);
		String msg1E = ecb.encrypt(msg1H);
		String msg2H = cap.asciiToHex(msg2);
		String msg2E = ecb.encrypt(msg2H);
		
		System.out.println(msg1 + "\n===== Se enkriptira vo =======");
		System.out.println(ecb.decrypt(msg1E));
		System.out.println("\n" + msg2 + "\n===== Se enkriptira vo =======");
		System.out.println(msg2E);
		
		System.out.println("\nPo cut and paste metodot primenet na prvata poraka");
		
		String altered = cap.replaceBlock(msg1E, msg2E, 0);
		String decrypted = ecb.decrypt(altered);
		System.out.println(altered + "\n" +cap.hexToAscii(decrypted).trim());
	}
}
