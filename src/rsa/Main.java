package rsa;

import java.util.Scanner;

public class Main {
	
	private static String bytesToString(byte[] encrypted)
    {
        String test = "";
        for (byte b : encrypted)
        {
            test += Byte.toString(b);
        }
        return test;
    }

	public static void main(String[] args) {
		RSA rsa = new RSA();
		Scanner sc = new Scanner(System.in);
        System.out.println("Enter the plain text:");
        String s = sc.nextLine();
        System.out.println("Encrypting String: " + s);
        System.out.println("String in Bytes: "
                + bytesToString(s.getBytes()));
        byte[] encrypted = rsa.encrypt(s.getBytes());
        byte[] decrypted = rsa.decrypt(encrypted);
        System.out.println("Decrypting Bytes: " + bytesToString(decrypted));
        System.out.println("Decrypted String: " + new String(decrypted));
        
        sc.close();
	}

}
