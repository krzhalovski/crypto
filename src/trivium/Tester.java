package trivium;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Tester {
	public File input;
	public Converter conv = new Converter();
	
	public Tester() throws FileNotFoundException {
		input = new File("src\\trivium\\test vectors.txt");
		
		Scanner scanner = new Scanner(input);
		String toProcess;
		
		while(!(toProcess=scanner.next()).equals("End")) {
			int setNumber;
			
			String key;
			String IV;
			
			ArrayList<String> check = new ArrayList<String>();
			ArrayList<String> xor = new ArrayList<String>();

			toProcess = scanner.next();
			//System.out.println(toProcess);
			setNumber = Integer.parseInt(toProcess.substring(0, 1));
			
			toProcess = scanner.next();
			
			if(toProcess.endsWith("#")) {
				toProcess = scanner.next();
			}

			scanner.next();
			scanner.next();
			key = scanner.next();

			scanner.next();
			scanner.next();
			IV = scanner.next();
			
			//System.out.println(key + " " + IV + " " + setNumber);
			
			for(int i = 0;i<24;i++) {
				toProcess = scanner.next();
				if(!(toProcess.startsWith("stream") || toProcess.equals("="))) {
					check.add(toProcess);
				}
				//System.out.println(i + " " + toProcess);
			}
			
			scanner.next();
			scanner.next();
			
			for(int i = 0;i<4;i++) {
				xor.add(scanner.next());
			}
			
			System.out.println("Testing key: " + key +" and IV: " + IV);
			if(test(key,IV,setNumber,check,xor)) {
				System.out.println("Testing succesfull\n");
			} else {
				System.out.println("Testing unsuccesfull\n");
				break;
			}
		}
		scanner.close();
	}
	
	public boolean test(String key, String IV, int sn, ArrayList<String> toMatch, ArrayList<String> xor) {
		
		String convertedKey = conv.hexToBinary(key);
		String convertedIV  = conv.hexToBinary(IV);
		
		RotatingState toBeTested = new RotatingState(convertedKey,convertedIV);
		
		int counter = 0;
		
		for(String match : toMatch) {
			if(counter==4) {
				toBeTested.generateNext32Hex();
				toBeTested.generateNext32Hex();
				toBeTested.generateNext32Hex();
				toBeTested.generateNext32Hex();
				toBeTested.generateNext32Hex();
				toBeTested.generateNext32Hex();
				toBeTested.generateNext32Hex();
				toBeTested.generateNext32Hex();
			}
			if(counter==12) {
				toBeTested.generateNext32Hex();
				toBeTested.generateNext32Hex();
				toBeTested.generateNext32Hex();
				toBeTested.generateNext32Hex();
				toBeTested.generateNext32Hex();
				toBeTested.generateNext32Hex();
				toBeTested.generateNext32Hex();
				toBeTested.generateNext32Hex();
			}
			String temp = conv.binaryToHex(toBeTested.generateNext32Hex());
			//System.out.println(match + " " + temp);
			if(!match.equals(temp)) return false;
			counter++;
		}
		
		return true;
	}
	
}
