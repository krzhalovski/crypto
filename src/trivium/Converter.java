package trivium;

import java.util.HashMap;

public class Converter {
	public HashMap<String, String> mapping = new HashMap<String, String>();
	public HashMap<String, String> unmapping = new HashMap<String, String> ();
	
	public Converter() {
		generateHexMaps();
	}
	
	public String binaryToHex(String input) {
		StringBuilder sb = new StringBuilder();
		
		for(int i =0; i < input.length();i+=8) {
			sb.append(this.binaryToHexUtil(input.substring(i, i+8)));
		}
		
		return sb.toString();
	}
	
	// Assumes string is little endian encoding and 8 bits
	public String binaryToHexUtil(String toConvert) {
		if(toConvert.length() < 8) return "";
		
		String firstDigit = new StringBuilder(toConvert.substring(4,8)).reverse().toString();
		String secondDigit = new StringBuilder(toConvert.substring(0,4)).reverse().toString();
		
		String converted = mapping.get(firstDigit) + mapping.get(secondDigit);
		return converted;
	}
	
	public String hexToBinary(String hex) {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0;i<hex.length();i+=2) {
			String toInsert = mapping.get(Character.toString(hex.charAt(i))) + mapping.get(Character.toString(hex.charAt(i+1)));
			sb.insert(0, toInsert);
		}
		
		return sb.toString();
	}
	
	public void generateHexMaps() {
		mapping.put("0000", "0");
		mapping.put("0001", "1");
		mapping.put("0010", "2");
		mapping.put("0011", "3");
		mapping.put("0100", "4");
		mapping.put("0101", "5");
		mapping.put("0110", "6");
		mapping.put("0111", "7");
		mapping.put("1000", "8");
		mapping.put("1001", "9");
		mapping.put("1010", "A");
		mapping.put("1011", "B");
		mapping.put("1100", "C");
		mapping.put("1101", "D");
		mapping.put("1110", "E");
		mapping.put("1111", "F");
		
		mapping.put("0", "0000");
		mapping.put("1", "0001");
		mapping.put("2", "0010");
		mapping.put("3", "0011");
		mapping.put("4", "0100");
		mapping.put("5", "0101");
		mapping.put("6", "0110");
		mapping.put("7", "0111");
		mapping.put("8", "1000");
		mapping.put("9", "1001");
		mapping.put("A", "1010");
		mapping.put("B", "1011");
		mapping.put("C", "1100");
		mapping.put("D", "1101");
		mapping.put("E", "1110");
		mapping.put("F", "1111");
	}
}
