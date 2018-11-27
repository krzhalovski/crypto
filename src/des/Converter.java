package des;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Converter {
	public static HashMap<String, String> mapping = new HashMap<String, String>();
	
	public Converter() {
	}
	
	public static String binaryToHex(String in) {
		StringBuilder out = new StringBuilder();
		
		for(int i = 0;i<in.length();i+=4) {
			out.append(mapping.get(in.substring(i, i+4)));
		}
		
		return out.toString();
	}
	
	public static String hexToBinary(String in) {
		StringBuilder out = new StringBuilder();
		
		for(int i = 0;i<in.length();i++) {
			out.append(mapping.get((Character.toString(in.charAt(i)))));
		}
		
		return out.toString();
	}
	
	public static String booleanToHex(boolean[] arr,int size) {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<size;i++) {
			if(arr[i]) sb.append("1");
			else sb.append("0");
		}
		return Converter.binaryToHex(sb.toString());
	}
	
	public static int booleanToInteger(boolean[] array) {
		int n = 0, l = array.length;
		for (int i = 0; i < l; ++i) {
		    n = (n << 1) + (array[i] ? 1 : 0);
		}
		return n;
	}
	
	// Option 1: Initial permutation, Option 2: Final permutation, Option 3: Parity drop permutation
	public static boolean[] permutePlainText(boolean[] text, int option) {
		boolean[] permuted = new boolean[64];
		
		Scanner scanner = openScanner(option);
		int i = 0;
		
		while (scanner.hasNext()){
			
			int position = scanner.nextInt();
			permuted[i] = text[position-1];
			i++;
			
		}
		
		scanner.close();
		return permuted;
	}
	
	private static Scanner openScanner(int option) {
		File file;
		if(option == 1) {
			file = new File("C:\\Users\\Ana\\eclipse-workspace\\crypto\\src\\des\\D_boxes\\Initial.txt");
		} else if (option == 2){
			file = new File("C:\\Users\\Ana\\eclipse-workspace\\crypto\\src\\des\\D_boxes\\Final.txt");
		} else if (option == 3){
			file = new File("C:\\Users\\Ana\\eclipse-workspace\\crypto\\src\\des\\D_boxes\\Parity.txt");
		} else if (option == 4){
			file = new File("C:\\Users\\Ana\\eclipse-workspace\\crypto\\src\\des\\D_boxes\\Compression.txt");
		} else if (option == 5){
			file = new File("src\\des\\D_boxes\\Selection.txt");
		} else {
			file = new File("src\\des\\D_boxes\\Feistel.txt");
		}
		try {
			return new Scanner(file);
		} catch (FileNotFoundException fnfe) {
			return null;
		}
	}
	
	public static void printBoolean(boolean[] arr) {
		for(int i = 0;i<arr.length;i++) {
			char temp = (arr[i]) ? '1' : '0';
			System.out.print(temp);
		}
		System.out.println("");
	}
	
	static {
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
