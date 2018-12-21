package des;

import java.io.FileNotFoundException;

public class CBC {
	Key key;
	DES des;
	String IV;
	String IVD;
	
	public CBC(Key key, String IV) throws FileNotFoundException {
		this.key = key;
		this.des = new DES(this.key);
		this.IV = IV;
		IVD = IV;
	}
	
	public String encrypt(String plaintext) {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0;i<plaintext.length();i+=16) {
			int end = Math.min(i+16, plaintext.length()-1);
			String toEncrypt = plaintext.substring(i, end);
			
			if(end!=i+16) toEncrypt = fillBlock(toEncrypt);
			String xored = this.xorIV(toEncrypt,IV);
			String encrypted = des.encryptNext64Bits(xored);
			sb.append(encrypted);
			this.IV = encrypted;
		}
		
		return sb.toString();
	}
	
	public String decrypt(String encryptedText) {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0;i<encryptedText.length();i+=16) {
			String toDecrypt = encryptedText.substring(i, i+16);
			String decrypted = des.decryptNext64Bits(Converter.hexToBinary(toDecrypt));
			String xored = this.xorIV(decrypted,IVD);
			sb.append(Converter.binaryToHex(xored));
			this.IVD = toDecrypt;
		}
		
		return sb.toString();
	}
	
	public String fillBlock(String s) {
		while(s.length() < 64) s+="0";
		return s;
	}
	
	public String xorIV(String s,String iv) {
		String convS = Converter.hexToBinary(s);
		String convIV = Converter.hexToBinary(iv);
		
		String res = "";
		for(int i = 0;i<64;i++) {
			if(convS.charAt(i) == convIV.charAt(i)) {
				res+='1';
			} else {
				res+='0';
			}
		}
		return res;
	}

}
