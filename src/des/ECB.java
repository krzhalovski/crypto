package des;

import java.io.FileNotFoundException;

public class ECB {
	Key key;
	DES des;
	
	public ECB(Key key) throws FileNotFoundException {
		this.key = key;
		this.des = new DES(this.key);
	}
	
	public String encrypt(String plaintext) {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0;i<plaintext.length();i+=16) {
			int end = Math.min(i+16, plaintext.length());
			String toEncrypt = plaintext.substring(i, end);
			
			if(end!=i+16) toEncrypt = fillBlock(toEncrypt);
			sb.append(des.encryptNext64Bits(Converter.hexToBinary(toEncrypt)));
		}
		
		return sb.toString();
	}
	
	public String decrypt(String encryptedText) {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0;i<encryptedText.length();i+=16) {
			sb.append(des.decryptNext64Bits(Converter.hexToBinary(encryptedText.substring(i, i+16))));
		}
		
		return sb.toString();
	}
	
	public String fillBlock(String s) {
		while(s.length() < 64) s+="0";
		return s;
	}
}
