package Leetcode;

public class Leetcode_167 {
    public static int[] toSum(int[] numbers,int target){
        int i=0,j=numbers.length-1;
        while (i < j) {
            int sum =numbers[i] + numbers[j];
            if(sum == target)
                return new int[]{i + 1, j + 1};
            else if(sum < target)
                i++;
            else
                 j--;
        }
        return null;
    }


    public static void main(String[] args) {
        int[] numbers = new int[]{
                2, 7, 11, 15
        };
        int[] targets = toSum(numbers, 9);
        for (int target : targets) {
            System.out.println(target);
        }
    }

}
