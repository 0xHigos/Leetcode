package Leetcode;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class Leetcode_378 {
    public int kthSmallest(int[][] matrix, int k) {
        Queue<Integer> queue = new PriorityQueue<>((o1,o2)->o2-o1);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                queue.offer(matrix[i][j]);
                if(queue.size() >k)
                    queue.poll();
            }
        }

        return queue.peek();
    }

    public static void main(String[] args) {
        int[][] matrix ={
                {1,5,9},
                {10,11,13},
                {12,13,15}
        };
        Leetcode_378 leetcode =new Leetcode_378();
        System.out.println(leetcode.kthSmallest(matrix, 8));
    }
    public int kthSmallest2(int[][] matrix,int k){
        int n=matrix.length;
        int left =matrix[0][0],right= matrix[n-1][n-1];
        while (left<right){
            int mid =left +(right -left) /2;
            int cnt =search(matrix,mid);
            if(cnt <k)
                left =mid+1;
            else
                right =mid;
        }
        return left;
    }

    private int search(int[][] matrix, int target) {
        int n =matrix.length;
        int i =0,j =n-1;
        int cnt =0;
        while (i<n && j>= 0){
            if(matrix[i][j] <=target){
                cnt +=j+1;
                i++;
            }else
                j--;
        }
        return cnt;
    }
}
