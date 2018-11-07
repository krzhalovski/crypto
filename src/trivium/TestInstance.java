package trivium;

import java.util.ArrayList;
import java.util.Scanner;

public class TestInstance {
	public int setNumber;
	public int vecNumber;
	
	public String key;
	public String IV;
	
	ArrayList<String> stream;
	ArrayList<Integer> startingPoints;
	ArrayList<String> xor;
	
	public TestInstance(int setN, int vecN, String key, String IV, ArrayList<String> stream, ArrayList<String> xor) {
		this.setNumber = setN;
		this.vecNumber = vecN;
		this.key = key;
		this.IV = IV;
		this.stream = stream;
		this.xor = xor;
	}
	
	public TestInstance() {
		stream = new ArrayList<String>();
		startingPoints = new ArrayList<Integer>();
		xor = new ArrayList<String> ();
	}
	
	public boolean generateInstance(Scanner input) {
		String nextLine = input.nextLine();
		while(nextLine.equals("" + "")) nextLine = input.nextLine();
		if(nextLine.startsWith("End")) return false;
		
		this.parseFirstLine(nextLine);
		
		nextLine = input.nextLine();
		this.getKey(nextLine);
		nextLine = input.nextLine();
		this.getIV(nextLine);
		
		for(int i = 0;i<16;i++) {
			nextLine = input.nextLine();
			
			if(i%4==0) {
				stream.add(nextLine.trim().split(" ")[2]);
				startingPoints.add(Integer.parseInt(nextLine.substring(nextLine.indexOf("[") + 1,nextLine.indexOf(".")))/16);
			}
			
			else stream.add(nextLine.trim());
		}
		
		for(int i = 0;i<4;i++) {
			xor.add(input.nextLine());
		}
		return true;
	}
	
	public void parseFirstLine(String firstLine) {
		this.setNumber = Integer.parseInt(firstLine.substring(4, 5));
		this.vecNumber = Integer.parseInt(firstLine.substring(14,17).trim());
	}
	
	public void getKey(String line) {
		this.key = line.trim().substring(6, 26);
	}
	
	public void getIV(String line) {
		this.IV = line.trim().substring(5, 25);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("Testing instance from set: %d with vector: %d\n", this.setNumber,this.vecNumber));
		sb.append(String.format("Key used: %s, IV used: %s\n", this.key, this.IV));
		return sb.toString();
	}
}
