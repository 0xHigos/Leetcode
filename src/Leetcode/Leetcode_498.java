package Leetcode;


/*
* Diagonal Traverse
* */
public class Leetcode_498 {
    public int[] findDiagonalOrder(int[][] matrix) {
        int r=0,c=0;
        int m=matrix.length;
        int n =matrix[0].length;
        int array[] = new int[m*n];
        for (int i = 0; i <array.length; i++) {
            array[i] =matrix[r][c];
            if ((r + c) % 2 == 0) {
                if(c == n-1){
                    r++;
                } else if (r == 0) {
                    c++;
                } else {
                    r--;
                    c++;
                }
            }else{
                if (r == m - 1) {
                    c++;
                } else if (c == 0) {
                    r++;
                }else{
                    r++;
                    c--;
                }
            }
        }
        return array;
    }
}
