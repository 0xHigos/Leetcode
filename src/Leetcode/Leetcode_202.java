package Leetcode;

import java.util.HashSet;

public class Leetcode_202 {
    public boolean isHappy(int n) {
        HashSet<Integer> hashSet = new HashSet<>();
        int temp = n;
        hashSet.add(n);
        while (true) {
            if (temp == 1)
                return true;
            temp = getNext(temp);
            if (hashSet.contains(temp))
                return false;
            hashSet.add(temp);
        }
    }

    private int getNext(int temp) {
        int result = 0;
        while (temp > 0) {
            result += (temp % 10) * (temp % 10);
            temp /= 10;
        }
        return result;
    }
}
