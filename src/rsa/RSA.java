package rsa;

import java.math.BigInteger;
import java.util.Random;

public class RSA {
	private BigInteger p;
	private BigInteger q;
	private BigInteger N;
    private BigInteger mp;
    private BigInteger e;
    private BigInteger d;
    private int bitlength = 1024;
    
    public RSA()
    {
        p = BigInteger.probablePrime(bitlength, new Random());
        q = BigInteger.probablePrime(bitlength, new Random());
        N = p.multiply(q);
        mp = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        mp = mp.divide(p.subtract(BigInteger.ONE).gcd(q.subtract(BigInteger.ONE)));
        e = BigInteger.probablePrime(bitlength / 2, new Random());
        while (mp.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(mp) < 0)
        {
            e.add(BigInteger.ONE);
        }
        d = e.modInverse(mp);
    }
 
    public RSA(BigInteger e, BigInteger d, BigInteger N)
    {
        this.e = e;
        this.d = d;
        this.N = N;
    }
 
    public byte[] encrypt(byte[] message)
    {
        return (new BigInteger(message)).modPow(e, N).toByteArray();
    }
 
    public byte[] decrypt(byte[] message)
    {
        return (new BigInteger(message)).modPow(d, N).toByteArray();
    }
}
