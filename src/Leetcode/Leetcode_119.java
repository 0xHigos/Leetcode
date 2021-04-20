package Leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Leetcode_119 {
    public static List<Integer> getRow(int rowIndex) {
        List<List<Integer>> lists =new ArrayList<>();
        if(rowIndex <0)
            return null;
        for(int i=0;i<=rowIndex;i++){
            List list =new ArrayList<>();
            for(int j=0;j<i+1;j++){
                if(j==0 || j==i)
                    list.add(1);
                else
                    list.add(lists.get(i-1).get(j-1) +lists.get(i-1).get(j));
            }
            lists.add(list);
        }
        return lists.get(rowIndex);
    }

    public static List<Integer> getRow2(int k) {
        Integer[] arr =new Integer[k+1];
        Arrays.fill(arr, 0);
        arr[0]=1;
        for (int i = 1; i <=k; i++) {
            for (int j = i; j >0 ; j--) {
                arr[j] =arr[j]+arr[j-1];
            }

        }
        return Arrays.asList(arr);
    }

    public static List<Integer> getRow3(int rowIndex) {
        LinkedList<Integer> ls =new LinkedList<>();
        long current =1;
        for (int i = 0; i <= rowIndex; i++) {
            ls.add((int) current);
            current =current*(rowIndex -i) /(i+1);
        }
        return ls;
    }
    public static void main(String[] args) {
        System.out.println(getRow3(3));
    }
}
