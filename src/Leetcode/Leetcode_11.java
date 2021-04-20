package Leetcode;

public class Leetcode_11 {
    //Brute Force
    public int maxAreaOne(int[] height) {
        int maxarea =0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i+1; j < height.length; j++) {
                maxarea = Math.max(maxarea, Math.min(height[i], height[j]) * (j - i));
            }
        }
        return maxarea;
    }
    // double pointer
    public int maxAreaTwo(int[] height) {
        int left =0,right=height.length-1;
        int maxArea=0;
        while (left < right) {
            maxArea = Math.max(maxArea, Math.min(height[left], height[right]) * (right - left));
            if(height[left] <height[right])
                left++;
            else
                right--;
        }
        return maxArea;
    }

    public static void main(String[] args) {
        Leetcode_11 leetcode11 =new Leetcode_11();
        System.out.println(leetcode11.maxAreaTwo(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
    }
}
