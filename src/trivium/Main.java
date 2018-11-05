package trivium;

public class Main {
	public static void main(String [] args) {
		String key1 = "80000000000000000000";
		String IV2 =  "00000000000000000000";
		
		String key = "00000000000000000000000000000000000000000000000000000000000000000000000010000000";
		String IV =  "00000000000000000000000000000000000000000000000000000000000000000000000000000000";
		
		RotatingState rs = new RotatingState(key,IV);
		for(int i = 0;i<264;i++)
		{
			if(rs.generateNextBit()==true)
				System.out.print("1");
			else
				System.out.print("0");
		}
	}
}
