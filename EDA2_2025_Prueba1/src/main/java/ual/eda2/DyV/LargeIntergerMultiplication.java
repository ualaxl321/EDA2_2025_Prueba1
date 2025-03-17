package main.java.ual.eda2.DyV;

import java.math.BigInteger;

public class LargeIntergerMultiplication {

    public static BigInteger multiplyDaC(BigInteger x, BigInteger y) {
        // cutoff to brute force
        int N = Math.max(x.bitLength(), y.bitLength());
        if (N <= 2000) return x.multiply(y);                // optimize this parameter

        // number of bits divided by 2, rounded up
        N = (N / 2) + (N % 2);

        // x = a + 2^N b,   y = c + 2^N d
        BigInteger b = x.shiftRight(N);
        BigInteger a = x.subtract(b.shiftLeft(N));
        BigInteger d = y.shiftRight(N);
        BigInteger c = y.subtract(d.shiftLeft(N));

        // compute sub-expressions
        BigInteger ac    = multiplyDaC(a, c);
        BigInteger bd    = multiplyDaC(b, d);
        BigInteger ad    = multiplyDaC(a, d);
        BigInteger bc    = multiplyDaC(b, c);
        BigInteger adbc = ad.add(bc);

        return ac.add(adbc.shiftLeft(N)).add(bd.shiftLeft(2*N));
    }

    public static BigInteger karatsubaAlgorithm(BigInteger x, BigInteger y) {
        // cutoff to brute force
        int N = Math.max(x.bitLength(), y.bitLength());
        if (N <= 2000) return x.multiply(y);                // optimize this parameter

        // number of bits divided by 2, rounded up
        N = (N / 2) + (N % 2);

        // x = a + 2^N b,   y = c + 2^N d
        BigInteger b = x.shiftRight(N);
        BigInteger a = x.subtract(b.shiftLeft(N));
        BigInteger d = y.shiftRight(N);
        BigInteger c = y.subtract(d.shiftLeft(N));

        // compute sub-expressions
        BigInteger ac    = karatsubaAlgorithm(a, c);
        BigInteger bd    = karatsubaAlgorithm(b, d);
        BigInteger abcd  = karatsubaAlgorithm(a.add(b), c.add(d));

        return ac.add(abcd.subtract(ac).subtract(bd).shiftLeft(N)).add(bd.shiftLeft(2*N));
    }

	public static void main(String[] args) {
		BigInteger x = new BigInteger("3141592653589793238462643383279502884197169399375105820974944592");
        BigInteger y = new BigInteger("2718281828459045235360287471352662497757247093699959574966967627");
		//BigInteger x = new BigInteger("1234567890123456789");
        //BigInteger y = new BigInteger("3456789012345678901");
        
        BigInteger resultK = karatsubaAlgorithm(x, y);
        System.out.println(resultK);

        BigInteger resultDaC = multiplyDaC(x, y);
        System.out.println(resultDaC);
	}
}	
