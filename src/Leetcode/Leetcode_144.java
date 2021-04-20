package Leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Leetcode_144 {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur=root;
        while (cur != null || !stack.isEmpty()) {
            while (cur !=null){
                stack.push(cur);
                list.add(cur.val);
                cur =cur.left;
            }
            cur =stack.pop();
            cur =cur.right;
        }
        return list;
    }
}
