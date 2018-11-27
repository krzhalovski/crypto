package des;

import java.io.FileNotFoundException;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {

		/*
		 * Analyzer analysis1 = new Analyzer("AABB09182736CCDD","123456ABCD132536");
		 * analysis1.fullAnalysis();
		 * 
		 * String in = "596F7572206C6970" + "732061726520736D" + "6F6F746865722074" +
		 * "68616E2076617365" + "6C696E650D0A0000"; Analyzer analysis2 = new
		 * Analyzer("0E329232EA6D0D73",in); analysis2.getEncryption();
		 */

		for (int i = 0; i < 16; i++) {
			for (int k = 0; k < 4; k++) {
				StringBuilder sb = new StringBuilder();
				for (int j = 0; j < i; j++) {
					sb.append("0");
				}
				sb.append((int)Math.pow(2, k));
				for(int j = i+1;j<16;j++) {
					sb.append("0");
				}
				
				Analyzer analysis3 = new Analyzer("0000000000000000", sb.toString());
				analysis3.fullAnalysis();
			}
		}

		/*Analyzer analysis3 = new Analyzer("0000000000000000", "0000000000000000");
		analysis3.fullAnalysis();*/

	}
}
