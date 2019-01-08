package rsa;

import java.math.BigInteger;

public class BreakN {
	public BigInteger N;
	public BigInteger sq;
	
	public BreakN(BigInteger N) {
		this.N = N;
		this.sq = getSq(N);
	}
	
	public BigInteger getSq(BigInteger x) {
		BigInteger div = BigInteger.ZERO.setBit(x.bitLength()/2);
	    BigInteger div2 = div;
	    for(;;) {
	        BigInteger y = div.add(x.divide(div)).shiftRight(1);
	        if (y.equals(div) || y.equals(div2))
	            return y;
	        div2 = div;
	        div = y;
	    }
	}
	
	public void getFirstNPrimes(BigInteger sq, int n) {
		for(int i = 0; i < n; i++) {
			System.out.println(sq);
			sq = sq.nextProbablePrime();
		}
	}
}
