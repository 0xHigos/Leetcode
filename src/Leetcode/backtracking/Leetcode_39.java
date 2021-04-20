package Leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Leetcode_39 {
    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        List<Integer> list = new ArrayList<>();
        backTracking(n, k, 1, list);
        return result;
    }
    public void backTracking(int n, int k, int start, List<Integer> list) {
        if(k <0)
            return;
        else if (k == 0) {
            result.add(new ArrayList<>());
        }else {
            for (int i = 1; i <=n; i++) {
                list.add(i);
                backTracking(n, k - 1, i + 1, list);
                list.remove(list.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        Leetcode_39 leetcode =new Leetcode_39();
        System.out.println(leetcode.combine(4, 2));
    }
}
