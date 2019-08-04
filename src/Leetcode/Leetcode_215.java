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

    //方法三：
    /*
     * 使用快排
     * O(N),O(1)
     *  */
    public static int findKthLargest(int[] nums, int k) {
        k = nums.length - k;
        int l = 0, h = nums.length - 1;
        while (l < h) {
            int j = partition(nums, l, h);
            if (j == k) {
                 break;
            } else if (j < k) {
                l=j+1;
            }else {
                h=j-1;
            }
        }
        return nums[k];
    }

    private static int partition(int[] nums, int l, int h) {
        int i = l, j = h + 1;
        while (true) {
            while (nums[++i] < nums[l] && i < h) ;
            while (nums[--j] > nums[l] && j > l) ;
            if (i >= j) {
                break;
            }
            swap(nums, i, j);
        }
        swap(nums, l, j);
        return j;
    }

    private static void swap(int[] nums, int i, int j) {
        int t=nums[i];
        nums[i] = nums[j];
        nums[j]=t;
    }


    public static void main(String[] args) {
        int[] nums = {
                3, 2, 1, 5, 6, 4
        };
        System.out.println(findKthLargest(nums, 2));
    }
}
