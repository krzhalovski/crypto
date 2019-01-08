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
        p = new BigInteger("167837152595905273866666106032178553716615674279844294117997219627886187504919283274577387219479787042032948804530244979489633803666690491518549363137601919321510983959546768452875936374022672607066667538902854959068940475407515422402251778224825346107748395022750807767175307318449717996840727709353141622191");
        q = new BigInteger("115620505641869051913093943962372520904930419088989432034862857918672450409085711715607034534732562078379720371224037016595592518498531598610241286981463714645753349402266645287573466578744471156433411173883148331683545303099371113681602608886820287239164795025469488749939456939861210353088463974823155809863");
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
    
    public void printParameters() {
    	System.out.println("p: " + p + "\nq: " + q + "\nN: " + N + "\ne: " + e + "\nd: " + d);
    }
}
