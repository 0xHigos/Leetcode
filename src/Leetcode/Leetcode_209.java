package Leetcode;

/*
*   Minimum Size Subarray Sum
* 给定一个含有 n 个正整数的数组和一个正整数 s ，
* 找出该数组中满足其和 ≥ s 的长度最小的连续子数组。
* 如果不存在符合条件的连续子数组，返回 0。
*
*  示例：
*       输入: s = 7, nums = [2,3,1,2,4,3]
*       输出: 2
*       解释: 子数组 [4,3] 是该条件下的长度最小的连续子数组。
*
* */
public class Leetcode_209 {
    public int minSubArrayLen(int s, int[] nums) {
        int i=0,j=0,min =Integer.MAX_VALUE;
        int sums = 0;
        while(j<nums.length){
            sums +=nums[j++];
            while(sums >=s){
                min =(min <j-i)?min:j-i;
                sums -=nums[i++];
            }
        }
        return min==Integer.MAX_VALUE?0:min;
    }

    public static void main(String[] args) {
        Leetcode_209 leetcode =new Leetcode_209();
        System.out.println(leetcode.minSubArrayLen(7, new int[]{
                2,3,1,2,4,3
        }));
    }

    public int solveNLogN(int s, int[] nums) {
        int[] sums = new int[nums.length + 1];
        for (int i = 1; i < sums.length; i++) {
            sums[i]=sums[i-1]+nums[i-1];
        }
        int minLen = Integer.MAX_VALUE;
        for (int i = 0; i < sums.length; i++) {
            int end = binarySeach(i + 1, sums.length - 1, sums[i] + s, sums);
            if(end ==sums.length)break;
            if(end-i<minLen)minLen =end-i;
        }
        return minLen ==Integer.MAX_VALUE?0:minLen;
    }

    private int binarySeach(int lo, int hi, int key, int[] sums) {
        while (lo <= hi) {
            int mid =(lo+hi) >>>1;
            if (sums[mid] >= key) {
                hi =mid-1;
            }else{
                lo=mid+1;
            }
        }
        return lo;
    }
}
