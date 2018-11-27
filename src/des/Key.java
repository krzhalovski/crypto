package des;

public class Key {
	public boolean bits[] = new boolean[64];
	public boolean noParityBits[] = new boolean [56];
	public boolean currentRoundKey[] = new boolean[48];
	int keyID = 0;
	
	public Key(String s) {
		 generateBits(s);
	}
	
	public Key(boolean[] bits) {
		for(int i = 0;i<64;i++)
			this.bits[i] = bits[i];
	}
	
	public void generateBits(String s) {
		if(s.length()==16) {
			s = Converter.hexToBinary(s);
		}
		
		if(s.length()==64) {
			for(int i=0; i<64;i++) {
				bits[i] = (s.charAt(i) == '1') ? true : false;
			}
		}
		
		noParityBits = dropParity();
		noParityBits = Converter.permutePlainText(bits, 3);
	}
	
	public boolean[] generateNextRoundKey() {
		boolean left[] = new boolean[28];
		boolean right[] = new boolean[28];
		
		for(int i = 0;i<28;i++) {
			left[i] = noParityBits[i];
			right[i] = noParityBits[i+28];
		}
		
		left = shiftLeft(left,keyID);
		right = shiftLeft(right,keyID);
		this.keyID++;
		
		for(int i = 0;i<28;i++) {
			noParityBits[i] = left[i];
			noParityBits[i+28] = right[i];
		}
		
		boolean []toReturn = Converter.permutePlainText(noParityBits, 4);
		return toReturn;
	}
	
	public boolean[] shiftLeft(boolean[] inBits,int ID) {
		inBits = shift(inBits);
		if(ID!=0 && ID!=1 && ID!=8 && ID !=15) inBits=shift(inBits);
		return inBits;
	}
	
	private boolean[] shift(boolean inBits[]) {
		boolean toReturn[] = new boolean[28];
		
		boolean temp = inBits[0];
		
		for(int j=1; j<28;j++) {
			toReturn[j-1] = inBits[j];
		}
		toReturn[27] = temp;
		return toReturn;
	}
	
	private boolean[] dropParity() {
		boolean reducedBits[] = new boolean[56];
		
		int counter = 0;
		for(int i = 0;i<64;i++) {
			if((i+1)%8==0) {
				counter++;
			} else {
				reducedBits[i-counter] = bits[i];
			}
		}
		return reducedBits;
	}
	
	public String toString() {
		return Converter.booleanToHex(bits,64);
	}
}
