package Leetcode;

public class leetcode_240 {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length ==0)
            return false;
        int shortMin = Math.min(matrix.length, matrix[0].length);
        for (int i = 0; i < shortMin; i++) {
            boolean verticlaFound =binarySearch(matrix,target,i,true);
            boolean horizonFound =binarySearch(matrix,target,i,false);
            if (verticlaFound || horizonFound) {
                return true;
            }
        }
        return false;
    }

    private boolean binarySearch(int[][] matrix, int target, int start, boolean vertical) {
        int ho =start;
        int hi=vertical ? matrix[0].length-1:matrix.length-1;
        while (ho <= hi) {
            int mid =(ho+hi) >>>1;
            if(vertical){
                if(matrix[start][mid] <target)
                    ho=mid+1;
                else if(matrix[start][mid] >target)
                    hi=mid-1;
                else return true;
            }else{
                if(matrix[mid][start] <target)
                    ho=mid+1;
                else if(matrix[mid][start] >target)
                    hi=mid-1;
                else return true;
            }

        }
        return false;

    }

    public boolean searchMatrix2(int[][] matrix, int target) {
        int row =matrix.length-1,column=0;
        while (row >=0 && column <=matrix[0].length){
            if(matrix[row][column] >target){
                row --;
            } else if (matrix[row][column] < target) {
                column++;
            }else
                return true;
        }
        return false;
    }
}
