package trivium;

public class Main {
	public static void main(String [] args) {
		String key = "10000000000000000000000000000000000000000000000000000000000000000000000000000000";
		String IV =  "00000000000000000000000000000000000000000000000000000000000000000000000000000000";
		
		State state = new State(key, IV);
		//System.out.println("Sequence: " + state.appendAtStart(true, state.sequence));
		
		for(int i = 0; i<264; i++) {
			if(state.generateNextBit())
				System.out.print(1);
			else
				System.out.print(0);
		}
		
		//debug.close();
	}
}
