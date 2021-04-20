## 						[Leetcode 104 二叉树的最大深度](https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/)

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

#### 自顶向下写法：

````java
class Solution {
    int result = 0;
    public int maxDepth(TreeNode root) {
        maxDepthHelper(root,1);
        return result;
    }

    private void maxDepthHelper(TreeNode root,int depth){
        if(root == null){
            return;
        }
        result = Math.max(result,depth);
        maxDepthHelper(root.left,depth +1);
        maxDepthHelper(root.right,depth +1);
    } 
}
````

#### 自低向上写法：

````java
class Solution {
    public int maxDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        return Math.max(leftDepth,rightDepth) +1;
    }
}
````

#### 广度优先遍历写法：

````java
class Solution {
    public int maxDepth(TreeNode root) {
        int result = 0;
        if(root == null){
            return result;
        }
        Queue<TreeNode> queue =new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            while(size > 0){
                TreeNode node =queue.poll();
                if(node.left != null){
                    queue.offer(node.left);
                }
                if(node.right != null){
                    queue.offer(node.right);
                }
                size --;
            }
            result ++;
        }
        return result;
    }
}
````

#### 其他技巧：

````java
class Solution {
    public int maxDepth(TreeNode root) {
        return root ==null ? 0 : Math.max(maxDepth(root.left),maxDepth(root.right)) +1;
    }
}
````

