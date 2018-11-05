package trivium;

import java.util.BitSet;
import java.util.Scanner;

public class State {
	BitSet key = new BitSet(80);
	BitSet IV = new BitSet(80);
	BitSet sequence = new BitSet(288);
	
	public State(String key, String IV) {
		read(key, IV);
		initialization();
	}
	
	public String printState() {
		StringBuilder sb1 = new StringBuilder();
		for(int i = 0; i<288; i++) {
			sb1.append(String.format("%4d", i));
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i<288; i++) {
			if(sequence.get(i) == false) sb.append(String.format("%4d", 0));
			else sb.append(String.format("%4d", 1));
		}
		
		return sb1.toString() + "\n" + sb.toString();
	}
	
	public boolean GFxor(boolean a, boolean b) {
		return a!=b;
	}
	
	public boolean GFand(boolean a, boolean b) {
		return b==true && a==true;
	}
	
	public void read(String key, String IV) {
		for(int i = 0 ;i<80; i++) {
			if(key.charAt(i)=='1')
				this.key.set(i,true);
			else
				this.key.set(i,false);
		}
		
		for(int i = 0 ;i<80; i++) {
			if(IV.charAt(i)=='1')
				this.IV.set(i,true);
			else
				this.IV.set(i,false);
		}
	}
	
	public BitSet appendAtStart(boolean bitToAppend, BitSet bs,int whichPart) {
		int size = 0;
		
		switch(whichPart) {
		case 0:
			size = 100;
			break;
		case 1:
			size = 84;
			break;
		case 2:
			size = 111;
			break;
		}
		
		BitSet toReturn = new BitSet(size);
		toReturn.set(0,bitToAppend);
		
		for(int i=1;i<size;i++) {
			toReturn.set(i, bs.get(i-1));
		}
		
		return toReturn;
	}
	// which part can be 0,1,2 depending on the part of the sequence to be changed
	public void setPart(int whichPart,BitSet toChange) {
		int offset = 0;
		int size = 0;
		
		switch (whichPart) {
		case 0:
			offset = 0;
			size = 100;
			break;
		case 1:
			offset = 100;
			size = 84;
			break;
		case 2:
			offset = 184;
			size = 111;
			break;
		}
		
		for(int i = 0; i<size; i++) {
			this.sequence.set(i+offset, toChange.get(i));
		}
	}
	
	public void setInitialSequence() {
		this.sequence.clear();
		this.sequence.set(285, true);
		this.sequence.set(286,true);
		this.sequence.set(287,true);
	}
	
	public void initialization() {
		this.setInitialSequence();
		this.setPart(0, this.key);
		this.setPart(1, IV);
		
		//System.out.println(this);
		Scanner debug = new Scanner(System.in);
		//System.out.println(this.printState());
		
		for(int i = 0; i < 4*288; i++) {
			//System.out.println(this.getSeqBit(65) + " " + this.getSeqBit(90) + " " +this.getSeqBit(91) +" " + this.getSeqBit(92) + " " +this.getSeqBit(170));
			//System.out.println(this.getSeqBit(161) + " " + this.getSeqBit(174) + " " +this.getSeqBit(175) +" " + this.getSeqBit(176) + " " +this.getSeqBit(263));
			//System.out.println(this.getSeqBit(242) + " " + this.getSeqBit(285) + " " +this.getSeqBit(286) +" " + this.getSeqBit(287) + " " +this.getSeqBit(68));
			
			//System.out.println(this.printState());
			//debug.nextLine();
			
			boolean t1 = operation(this.getSeqBit(65),this.getSeqBit(90),this.getSeqBit(91),this.getSeqBit(92),this.getSeqBit(170));
			boolean t2 = operation(this.getSeqBit(161),this.getSeqBit(174),this.getSeqBit(175),this.getSeqBit(176),this.getSeqBit(263));
			boolean t3 = operation(this.getSeqBit(242),this.getSeqBit(285),this.getSeqBit(286),this.getSeqBit(287),this.getSeqBit(68));
			
			//System.out.println(t1);
			//System.out.println(t2);
			//System.out.println(t3);
			
			BitSet temp1 = this.sequence.get(0, 92);
			BitSet firstPart = this.appendAtStart(t3, temp1,0);
			
			BitSet temp2 = this.sequence.get(93, 176);
			BitSet secondPart = this.appendAtStart(t1, temp2,1);
			
			BitSet temp3 = this.sequence.get(177, 287);
			BitSet thirdPart = this.appendAtStart(t2, temp3,2);
			
			this.setPart(0, firstPart);
			this.setPart(1, secondPart);
			this.setPart(2, thirdPart);
		}
		
		debug.close();
	}
	
	public boolean generateNextBit() {
		
		boolean nextBit;
		
		boolean t1 = GFxor(this.getSeqBit(65),this.getSeqBit(92));
		boolean t2 = GFxor(this.getSeqBit(161),this.getSeqBit(176));
		boolean t3 = GFxor(this.getSeqBit(242),this.getSeqBit(287));
		
		nextBit = this.GFxor(GFxor(t1,t2),t3);
		
		t1 = operation(this.getSeqBit(65),this.getSeqBit(90),this.getSeqBit(91),this.getSeqBit(92),this.getSeqBit(170));
		t2 = operation(this.getSeqBit(161),this.getSeqBit(174),this.getSeqBit(175),this.getSeqBit(176),this.getSeqBit(263));
		t3 = operation(this.getSeqBit(242),this.getSeqBit(285),this.getSeqBit(286),this.getSeqBit(287),this.getSeqBit(68));
		
		//System.out.println(t1);
		//System.out.println(t2);
		//System.out.println(t3);
		
		BitSet temp1 = this.sequence.get(0, 100);
		BitSet firstPart = this.appendAtStart(t3, temp1,0);
		
		BitSet temp2 = this.sequence.get(100, 176);
		BitSet secondPart = this.appendAtStart(t1, temp2,1);
		
		BitSet temp3 = this.sequence.get(177, 287);
		BitSet thirdPart = this.appendAtStart(t2, temp3,2);
		
		this.setPart(0, firstPart);
		this.setPart(1, secondPart);
		this.setPart(2, thirdPart);
		
		return nextBit;
	}
	
	// s1 + s2*s3 + s4 + s5
	public boolean operation(boolean a, boolean b, boolean c, boolean d, boolean e) {
		boolean temp = this.GFand(b, c);
		
		return GFxor(GFxor(GFxor(a,temp),d),e);
		//return GFxor(GFxor(GFand(GFxor(a,b),c),d),e); //if and doesn't have precedence
	}
	
	public boolean getSeqBit(int x) {
		return this.sequence.get(x);
	}
	
	public String toString() {
		return "Sequence: " + sequence;
	}
}
