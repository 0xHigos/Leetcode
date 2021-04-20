package Leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

//找出数组中第K大的数字
public class Leetcode_215 {
    //方法一：
    /*
     * 先排序，然后直接返回长度减k 的数字
     * O(NlogN), O(1)
     * */
    public int findKthLargestOne(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }


    //方法二：
    /*
     * 使用堆排序
     * O(NLogK),O(K)
     *
     * */
    public static int findKthLargestTwo(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int val : nums) {
            pq.add(val);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        return pq.peek();
    }

    public static int findKthLargestThree(int[] nums, int k) {
        k = nums.length - k;
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int j = partition(nums, low, high);
            if (j == k)
                break;
            else if (j > k)
                high = j - 1;
            else
                low = j + 1;
        }
        return nums[k];
    }

    private static int partition(int[] nums, int low, int high) {
        int i = low;
        int j = high+1;
        while (true) {
            while (i < high && nums[++i] < nums[low]);
            while (j > low && nums[low] < nums[--j]);
            if (i >= j)
                break;
            swap(nums, i, j);
        }
        swap(nums, low, j);
        return j;
    }


    private static void swap(int[] nums, int i, int j) {
        int t=nums[i];
        nums[i] = nums[j];
        nums[j]=t;
    }


    public static void main(String[] args) {
        int[] nums = {
                3,2,1,5,6,4
        };
        System.out.println(findKthLargestThree(nums, 2));
    }
}
