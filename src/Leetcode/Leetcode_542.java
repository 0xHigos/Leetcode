package Leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class Leetcode_542 {
    private int[][] neighbors ={
            {-1,0},
            {0,-1},
            {1,0},
            {0,1}
    };
    private Queue<int[]> queue =new LinkedList<>();
    public int[][] updateMatrix(int[][] matrix){
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[0].length; j++) {
                if(matrix[i][j] == 0)
                    queue.offer(new int[]{i,j});
                else
                    matrix[i][j]=10001;
            }
        while(!queue.isEmpty()){
            int size =queue.size();
            while(size -- >0){
                int[] tmp =queue.poll();
                int min =matrix[tmp[0]][tmp[1]];
                for (int[] neighbor : neighbors) {
                    int x =tmp[0]+neighbor[0];
                    int y =tmp[1]+neighbor[1];
                    if(x >=0 && x <=matrix.length-1
                    && y>=0 && y<=matrix[0].length-1){
                        min =Math.min(matrix[x][y]+1,min);
                        if(matrix[x][y] ==10001)
                            queue.offer(new int[]{x,y});
                    }
                }
                matrix[tmp[0]][tmp[1]] =min;
            }

        }
        return matrix;
    }
    /*
    public int[][] updateMatrix(int[][] matrix) {
        if(matrix.length==0) return matrix;

        for(int i = 0; i<matrix.length; i++)
            for(int j = 0; j<matrix[0].length; j++)
                if(matrix[i][j]==1&&!hasNeiberZero(i, j,matrix))
                    matrix[i][j] = matrix.length+matrix[0].length+1;

        for(int i = 0; i<matrix.length; i++)
            for(int j = 0; j<matrix[0].length; j++)
                if(matrix[i][j]==1)
                    dfs(matrix, i, j, -1);

        return matrix;
    }
    private void dfs(int[][] matrix, int x, int y, int val){
        if(x<0||y<0||y>=matrix[0].length||x>=matrix.length||matrix[x][y]<=val)
            return;

        if(val>0) matrix[x][y] = val;

        dfs(matrix, x+1, y, matrix[x][y]+1);
        dfs(matrix, x-1, y, matrix[x][y]+1);
        dfs(matrix, x, y+1, matrix[x][y]+1);
        dfs(matrix, x, y-1, matrix[x][y]+1);

    }
    private boolean hasNeiberZero(int x, int y, int[][] matrix){
        if(x>0&&matrix[x-1][y]==0) return true;
        if(x<matrix.length-1&&matrix[x+1][y]==0) return true;
        if(y>0&&matrix[x][y-1]==0) return true;
        if(y<matrix[0].length-1&&matrix[x][y+1]==0) return true;

        return false;
    }
    */
}
