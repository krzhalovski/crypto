package trivium;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Tester {
	public File input;
	public Converter conv = new Converter();
	
	public Tester() throws FileNotFoundException {
		input = new File("src\\trivium\\test vectors.txt");
	}
	
	public void initiateTests() throws FileNotFoundException {
		Scanner scanner = new Scanner(input);
		TestInstance toTest = new TestInstance();
		
		while(toTest.generateInstance(scanner)) {
			System.out.println(toTest);
			
			if(test(toTest)) {
				System.out.println("Testing succesfull\n");
			} else {
				System.out.println("Testing unsuccesfull\n");
				break;
			}
			toTest = new TestInstance();
		}
		scanner.close();
	}
	
	public boolean test(TestInstance ti) {
		
		String convertedKey = conv.hexToBinary(ti.key);
		String convertedIV  = conv.hexToBinary(ti.IV);
		
		RotatingState toBeTested = new RotatingState(convertedKey,convertedIV);
		
		int counter = 0;
		int startingPointIndex = 0;
		System.out.println(ti.startingPoints);
		 
		while(counter < 16) {
			while(toBeTested.generatedHexValues < ti.startingPoints.get(startingPointIndex)) {
				toBeTested.generateNext32Hex();
			}
			
			for(int i = 0; i<4; i++) {
				String temp = conv.binaryToHex(toBeTested.generateNext32Hex());
				if(!ti.stream.get(counter).equals(temp)) return false;
				counter++;
			}
			startingPointIndex++;
		}
		
		return true;
	}
	
	public void getStreamBoundaries() {
		
	}
	
}
