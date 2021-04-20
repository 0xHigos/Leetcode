package Leetcode;

public class Leetcode_748 {
    public int dominantIndex(int[] nums) {
        int first=nums[0] ,second=0,index =0;
        for (int i = 1; i < nums.length; i++) {
            if(first <nums[i]){
                second =first;
                first =nums[i];
                index =i;
            }else if(nums[i] >second){
                second =nums[i];
            }
        }
        return (first >=(second<<1))?index:-1;
    }

    public static void main(String[] args) {
        Leetcode_748 leetcode =new Leetcode_748();
        System.out.println(leetcode.dominantIndex(new int[]{
               0,0,3,2
        }));
    }
}
