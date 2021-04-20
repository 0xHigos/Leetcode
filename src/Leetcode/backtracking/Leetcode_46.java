package Leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Leetcode_46 {
    private List<List<Integer>> result =new ArrayList<List<Integer>>();
    private int length;

    public List<List<Integer>> permute(int[] nums) {
        length = nums.length;
        List<Integer> select = new ArrayList<>();
        helper(select, nums, 0);
        return result;
    }
    //select 代表已取出的数，nums则是有所有数的池子，pos代表要取第几个位置的数
    private void helper(List<Integer> select, int[] nums, int pos) {
        //跳出条件是已取了池子里所有的数，完成一次排列
        if(pos ==length){
            result.add(new ArrayList<Integer>(select));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            int num =nums[i];
            // 取过的数不再取
            if (select.contains(num)) {
                continue;
            }
            select.add(num);
            helper(select, nums, pos + 1);
            //遍历此节点后，回溯到上一步，因此要把加入到结果中的此点去除掉！
            select.remove(select.size() - 1);
        }
    }
}

