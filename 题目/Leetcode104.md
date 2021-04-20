## 						[Leetcode 101](https://leetcode-cn.com/problems/symmetric-tree/ )

#### 数据结构定义：

````java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
````

#### 递归写法：

````java
class Solution {
    public boolean isSymmetric(TreeNode root) {
        if(root ==null){
            return  true;
        }
        return checkSymmetric(root.left,root.right);
    }
    
    public boolean checkSymmetric(TreeNode left,TreeNode right){
        if(left == null && right == null){
            return true;
        }
        if(left ==null || right == null){
            return false;
        }
        return left.val == right.val &&
            checkSymmetric(left.left,right.right) &&
            checkSymmetric(left.right,right.left);
    }
}
````

#### 队列：

````java
class Solution {
    public boolean isSymmetric(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode left = queue.poll();
            TreeNode right = queue.poll();
            if(left == null && right == null){
                continue;
            }
            if(left ==null || right == null || left.val != right.val){
                return false;
            }
            queue.offer(left.left);
            queue.offer(right.right);

            queue.offer(left.right);
            queue.offer(right.left);
        }
        return true;
    }
}
````

