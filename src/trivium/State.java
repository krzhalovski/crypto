package trivium;

import java.util.BitSet;

public class State {
	BitSet key = new BitSet(80);
	BitSet IV = new BitSet(80);
	BitSet sequence = new BitSet(288);
	
	public State(String key, String IV) {
		read(key, IV);
		initialization();
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
		
		for(int i = 0; i<288; i++) {
			this.sequence.set(i,false);
		}
	}
	
	public void initialization() {
		for(int i=0;i<93;i++) {
			sequence.or(key);
		}
	}
	
	public String toString() {
		return "Key: " + key + "\nIV: " + IV + "\nSequence: " + sequence;
	}
}
