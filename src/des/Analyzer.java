package des;

import java.io.FileNotFoundException;
import java.util.Arrays;

public class Analyzer {
	Key key;
	DES des;
	String plainText;
	
	public Analyzer(String key,String text) throws FileNotFoundException {
		this.key = new Key(key);
		this.des = new DES(this.key);
		this.plainText = text;
		
	}
	
	public void printRoundKeys() {
		for(int i = 0;i<16;i++) {
			System.out.println("Round key "+ (i+1) + ": " + Converter.booleanToHex(des.roundKeys[i],48));
		}
	}
	
	public void encryptStepByStep() {
		for(int i = 0;i<plainText.length(); i+=16) {
			String block = plainText.substring(i, i + 16);
			System.out.println("=========================================");
			System.out.println("Converting the block of text "+((i/16)+1) + ": " + block);
			
			block = Converter.hexToBinary(block);
			boolean []initial = des.initialPermutation(block);
			System.out.println("Initial permutation: " + Converter.booleanToHex(initial, 64));
			System.out.println("=========================================");
			
			boolean left[] = Arrays.copyOfRange(initial, 0, 32);
			boolean right[] = Arrays.copyOfRange(initial, 32, 64);
			
			String finalPermutation = des.initiateRound(left, right, 0, true);
			
			System.out.println("Final text: " + finalPermutation);
			System.out.println("=========================================");
		}
	}
	
	public void fullAnalysis() {
		System.out.println("Analysing encryption of:\n" + this.plainText + "\nUsing the key:\n" +this.key);
		System.out.println("=========================================");
		this.printRoundKeys();
		this.encryptStepByStep();
	}
	
	public void getEncryption() {
		System.out.println(des.encryptText(plainText));
	}
}
