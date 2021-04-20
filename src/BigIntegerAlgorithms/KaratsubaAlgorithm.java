package BigIntegerAlgorithms;

import java.util.Arrays;

/*
* Write
* */
public class KaratsubaAlgorithm {
    public static long karatsuba(long num1, long num2) {
        if (num1 < 10 || num2 < 10)
            return num1 * num2;
        int size1 = String.valueOf(num1).length();
        int size2 = String.valueOf(num2).length();
        int half = Math.max(size1, size2) / 2;
        long a = Long.valueOf(String.valueOf(num1).substring(0, size1 - half));
        long b = Long.valueOf(String.valueOf(num1).substring(size1 - half));
        long c = Long.valueOf(String.valueOf(num1).substring(0, size2 - half));
        long d = Long.valueOf(String.valueOf(num1).substring(size1 - half));
        long ac = karatsuba(a, c);
        long bd = karatsuba(b, d);
        long adbc=karatsuba((a+b),(c+d))-ac-bd;
        return (long) (ac*Math.pow(10,half<<1) +bd+adbc*Math.pow(10,half));
    }
    

}
