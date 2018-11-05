package trivium;

import java.util.ArrayList;

public class RotatingState {
	ArrayList<Boolean> bits = new ArrayList<Boolean>();
	ArrayList<Boolean> key = new ArrayList<Boolean>();
	ArrayList<Boolean> IV = new ArrayList<Boolean>();
	
	public RotatingState(String key, String IV) {
		for(int i = 0;i<80;i++) {
			if(key.charAt(i) == '1') {
				this.key.add(true);
			} else {
				this.key.add(false);
			}
		}
		
		for(int i = 0;i<80;i++) {
			if(IV.charAt(i) == '1') {
				this.IV.add(true);
			} else {
				this.IV.add(false);
			}
		}
		
		initialize();
	}
	
	public void initialize_util() {
		
		//Adding all the key bytes in the sequence
		bits.addAll(key);
		
		// Filling the other 20 bits with false
		for(int i = 0;i<20;i++) bits.add(false);
		
		// Adding the IV 
		for(int i = 0;i<80;i++) bits.add(IV.get(i));
		
		// Adding zeroes in the leftover bits
		for(int i = 0;i<112;i++) bits.add(false);
		
		// Adding three true bytes at the end
		bits.add(true);
		bits.add(true);
		bits.add(true);
	}
	
	public void initialize() {
		// Creating the initial sequence
		initialize_util();
		
		// Rotating the state 4*288 times
		for(int i = 0;i<4*288;i++) {
			this.generateNextBit();
		}
	}
	
	// Logic for the stream generation
	public boolean generateNextBit() {
		
		boolean a1 = this.bits.get(90) & this.bits.get(91);
		boolean a2 = this.bits.get(181) & this.bits.get(182);
		boolean a3 = this.bits.get(292) & this.bits.get(293);
		
		boolean t1 = this.bits.get(65) ^ this.bits.get(92);
		boolean t2 = this.bits.get(168) ^ this.bits.get(183);
		boolean t3 = this.bits.get(249) ^ this.bits.get(294);
		
		boolean res = t1 ^ t2 ^ t3;
		
		boolean s1 = a1 ^ this.bits.get(177) ^ t1;
		boolean s2 = a2 ^ this.bits.get(270) ^ t2;
		boolean s3 = a3 ^ this.bits.get(68) ^ t3;
		
		rotateState();
		
		this.bits.set(0,s3);
		this.bits.set(100,s1);
		this.bits.set(184, s2);
		
		return res;
	}
	
	// Utility function for rotating the state
	public void rotateState() {
		this.bits.add(0,true);
		this.bits.remove(this.bits.size()-1);
	}
	
	public String generateNext32Hex() {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0;i<128;i++)
		{
			if(generateNextBit()==true)
				sb.append("1");
			else
				sb.append("0");
		}
		
		return sb.toString();
	}
	
	// Utility function for debugging
	public void printState() {
		bits.stream().forEachOrdered(i -> {if(i==true) System.out.print("1"); else System.out.print("0");});
	}
}
