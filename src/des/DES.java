package des;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class DES {
	boolean roundKeys[][] = new boolean[16][48];
	Key initialKey;

	int s_boxes[][][];

	public DES(Key key) throws FileNotFoundException {
		initialKey = key;
		generateRoundKeys();
		initializeSBoxes();
	}

	private void initializeSBoxes() throws FileNotFoundException {
		s_boxes = new int[8][4][16];

		for (int i = 1; i <= 8; i++) {
			String directory = "src//des//S_boxes//S";
			directory += i + ".txt";

			File f = new File(directory);
			Scanner s = new Scanner(f);

			for (int j = 0; j < 4; j++) {
				for (int k = 0; k < 16; k++) {
					s_boxes[i - 1][j][k] = s.nextInt();
				}
			}
			s.close();
		}
	}

	private void generateRoundKeys() {
		for (int i = 0; i < 16; i++) {
			boolean generatedKey[] = initialKey.generateNextRoundKey();

			for (int j = 0; j < 48; j++) {
				roundKeys[i][j] = generatedKey[j];
			}
		}
	}

	public String encryptText(String plainText) {
		plainText = Converter.hexToBinary(plainText);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < plainText.length(); i += 64) {
			sb.append(encryptNext64Bits(plainText.substring(i, i + 64)));
		}
		return sb.toString();
	}
	
	public String decryptText(String plainText) {
		plainText = Converter.hexToBinary(plainText);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < plainText.length(); i += 64) {
			sb.append(decryptNext64Bits(plainText.substring(i, i + 64)));
		}
		return sb.toString();
	}

	public String encryptNext64Bits(String current) {
		boolean initial[] = initialPermutation(current);
		
		boolean left[] = Arrays.copyOfRange(initial, 0, 32);
		boolean right[] = Arrays.copyOfRange(initial, 32, 64);

		String afterRounds = initiateRound(left, right, 0, 1);

		return afterRounds;
	}
	
	public String decryptNext64Bits(String current) {
		boolean initial[] = initialPermutation(current);
		
		boolean left[] = Arrays.copyOfRange(initial, 0, 32);
		boolean right[] = Arrays.copyOfRange(initial, 32, 64);

		String afterRounds = initiateRound(left, right, 15, -1);

		return afterRounds;
	}

	public String initiateRound(boolean[] left, boolean[] right, int iteration, int direction) {
		if ( (iteration == 16 && direction == 1) || (iteration == -1 && direction == -1) ){
			boolean[] finalPermutation = new boolean[64];
			for (int i = 0; i < 32; i++) {
				finalPermutation[i] = right[i];
				finalPermutation[i + 32] = left[i];
			}

			finalPermutation = Converter.permutePlainText(finalPermutation, 2);
			return Converter.booleanToHex(finalPermutation,64);
		}
		
		boolean resLeft[] = right;
		boolean resRight[] = feistel(right, this.roundKeys[iteration]);

		for (int i = 0; i < 32; i++) {
			resRight[i] ^= left[i];
		}
		return initiateRound(resLeft, resRight, iteration + direction, direction);
	}

	public boolean[] sMagic(boolean block[]) {
		boolean[] toReturn = new boolean[48];

		for (int i = 0; i < 8; i++) {
			boolean toBeMapped[] = Arrays.copyOfRange(block, i * 6, i * 6 + 6);
			boolean mapped[] = sMap(toBeMapped, i);

			for (int j = 0; j < 4; j++) {
				toReturn[i * 4 + j] = mapped[j];
			}
		}

		return toReturn;
	}

	public boolean[] sMap(boolean[] bit6, int s_box) {
		//System.out.print("S-Box" + (s_box+1) + ": ");
		//Converter.printBoolean(bit6);
		boolean[] toReturn = new boolean[4];

		boolean[] row = new boolean[2];
		boolean[] column = new boolean[4];

		row[0] = bit6[0];
		row[1] = bit6[5];
		column[0] = bit6[1];
		column[1] = bit6[2];
		column[2] = bit6[3];
		column[3] = bit6[4];

		int r = Converter.booleanToInteger(row);
		int c = Converter.booleanToInteger(column);

		int number = s_boxes[s_box][r][c];
		String binary = Integer.toBinaryString(number);

		for (int i = 0; i < 4; i++) {
			toReturn[i] = false;
		}
		int counter = 0;
		for (int i = 4 - binary.length(); i < 4; i++) {
			toReturn[i] = binary.charAt(counter) == '1';
			counter++;
		}

		return toReturn;
	}

	public boolean[] feistel(boolean block[], boolean key[]) {
		boolean expanded[] = expand(block);

		for (int i = 0; i < 48; i++) {
			expanded[i] ^= key[i];
		}

		return Converter.permutePlainText(sMagic(expanded), 6);
	}

	public boolean[] expand(boolean toExpand[]) {
		return Converter.permutePlainText(toExpand, 5);
	}

	public boolean[] initialPermutation(String s) {
		boolean[] initial = new boolean[64];
		for (int i = 0; i < 64; i++) {
			initial[i] = (s.charAt(i) == '1') ? true : false;
		}
		return Converter.permutePlainText(initial, 1);
	}
}
