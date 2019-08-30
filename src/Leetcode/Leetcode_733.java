package Leetcode;

public class Leetcode_733 {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int color = image[sr][sc];
        if(color == newColor)
            return image;
        DFS(image,sr,sc,color,newColor);
        return image;
    }

    private void DFS( int[][] image, int sr, int sc, int color, int newColor) {
        if(sr <0 ||sr >=image.length || sc <0 || sc>=image[0].length || image[sr][sc]!=color)
            return;
        image[sr][sc] =newColor;
        DFS(image,sr+1,sc,color,newColor);
        DFS(image,sr-1,sc,color,newColor);
        DFS(image,sr,sc+1,color,newColor);
        DFS(image,sr,sc-1,color,newColor);
    }

    public static void main(String[] args) {
        System.out.println(String.valueOf('b'));
    }
}
