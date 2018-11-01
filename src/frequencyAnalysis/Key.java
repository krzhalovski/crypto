package frequencyAnalysis;

import java.util.HashMap;
import java.util.Map.Entry;

public class Key {
	public HashMap<Character, Character> encode = new HashMap<Character,Character>();
	public HashMap<Character, Character> decode = new HashMap<Character,Character>();
	
	public Key(String from, String to) {
		for(int i =0;i<from.length();i++) {
			encode.put(from.charAt(i), to.charAt(i));
			decode.put(to.charAt(i), from.charAt(i));
		}
	}
	
	public String encode(String input) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0;i<input.length();i++) {
			Character encoded = encode.get(input.charAt(i));
			if(encoded!=null)
				sb.append(encoded);
			else
				sb.append(input.charAt(i));
		}
		return sb.toString();
	}
	
	public String decode(String input) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0;i<input.length();i++) {
			Character decoded = decode.get(input.charAt(i));
			if(decoded!=null)
				sb.append(decode.get(input.charAt(i)));
			else
				sb.append(input.charAt(i));
		}
		return sb.toString();
	}
	
	public void swap(char a, char b) { // swap(i so t) x -> f y -> i
		char x = encode.get(a);
		char y = encode.get(b);
		//System.out.println(x + " " +y);
		decode.put(x, b);
		decode.put(y, a);
		encode.put(b, x);
		encode.put(a, y);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(Entry<Character, Character> e : decode.entrySet()) {
			sb.append(e.getKey() + "->" + e.getValue() + ",");
		}
		return sb.toString();
	}
}
