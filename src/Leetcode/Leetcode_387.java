package Leetcode;

public class Leetcode_387 {
    public int firstUniqChar(String s) {
        int[] nums = new int[26];
        for (char c : s.toCharArray())
            nums[c-'a']++;
        for (int i = 0; i < nums.length; i++) {
            if(nums[s.charAt(i)-'a']==1)
                return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        Leetcode_387 leetcode =new Leetcode_387();
        System.out.println(leetcode.firstUniqChar("leetcode"));
    }
}
