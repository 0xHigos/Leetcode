package Leetcode;

/*
*
*      求众数
*   给定一个大小为 n 的数组，找到其中的众数。众数是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
    你可以假设数组是非空的，并且给定的数组总是存在众数。
* */
public class Leetcode_169 {

    public static void main(String[] args) {
        System.out.println(majorityElement3(new int[]{
                8,9,8,9,8
        }));
    }

    public static int majorityElement(int[] nums) {
        int temp=nums[0],count=1;
        int res=0;
        for(int i=1;i<nums.length;i++){
            if(nums[i]==temp){
                count++;
            }else{
                count --;
                if(count ==0 ){
                    count=1;
                    temp=nums[i];
                }
            }
        }
        if(count >0){
            for(Integer num :nums){
                if(temp ==num){
                    res ++;
                }
            }
        }

        return (res>nums.length/2)?temp:0;
    }


    public static int majorityElement2(int[] nums) {
        int count =0,candidate =0;
        for (int num : nums) {
            if(count ==0){
                candidate =num;
                count ++;
            } else if (candidate == num) {
                count ++;
            }else{
                count --;
            }

        }
        count =0;
        for (int num : nums) {
            if (candidate == num) {
                count ++;
            }
        }
        return count>nums.length/2?candidate:0;
    }

    public static int majorityElement3(int[] nums) {
        if (nums.length < 2) {
            return nums[0];
        }
        int curNumber =nums[0],count =0;
        for (int num : nums) {
            ; count =curNumber ==num?count+1:count-1;
            if (count == 0) {
                count =1;
                curNumber =num;
            }
        }
        return count>0?curNumber:0;
    }
}
