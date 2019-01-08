package rsa;

import java.util.Scanner;

public class Main {
	
	private static String bytesToString(byte[] encrypted)
    {
        String test = "";
        for (byte b : encrypted)
        {
            test += Byte.toString(b) + " ";
        }
        return test.trim();
    }

	public static void main(String[] args) {
		RSA rsa = new RSA();
		Scanner sc = new Scanner(System.in);
        System.out.println("Enter the plain text:");
        String s = sc.nextLine();
        System.out.println("Encrypting String: " + s);
        byte[] encrypted = rsa.encrypt(s.getBytes());
        System.out.println("Encrypted Message: " + bytesToString(encrypted));
        byte[] decrypted = rsa.decrypt(encrypted);
        System.out.println("Decrypted Message: " + new String(decrypted));
        rsa.printParameters();
        sc.close();
	}

}
