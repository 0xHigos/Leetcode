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

    public static void main(String[] args) {
//        String a = "1234567891011121314151617181920";
//        String b = "2019181716151413121110987654321";

//        String a = "999999999999";
//        String b = "999999999999";

//        String a = "24566";
//        String b = "452053";

        String a = "98";
        String b = "21";

        char[] charArr1 = a.trim().toCharArray();
        char[] charArr2 = b.trim().toCharArray();

        // 字符数组转换为int[]数组
        int[] arr1 = new int[charArr1.length];
        int[] arr2 = new int[charArr2.length];
        for(int i = 0; i < charArr1.length; i++){
            arr1[i] = charArr1[i] - '0';
        }
        for(int i = 0; i < charArr2.length; i++){
            arr2[i] = charArr2[i] - '0';
        }

        // 开始计算
        System.out.println(XiaoxueAlgorithm.bigNumberMultiply2(arr1, arr2));

    }

}
