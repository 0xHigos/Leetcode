

### 题目定义：

````java
输入一棵二叉树的根节点，求该树的深度。从根节点到叶节点依次经过的节点（含根、叶节点）形成树的一条路径，最长路径的长度为树的深度。

例如：

给定二叉树 [3,9,20,null,null,15,7]，

    3
   / \
  9  20
    /  \
   15   7
返回它的最大深度 3 。

 
提示：

节点总数 <= 10000

````

### 递归方式：

````java
class Solution {
    public int maxDepth(TreeNode root) {
        if(root == null)
            return 0;
        return Math.max(maxDepth(root.left) ,maxDepth(root.right)) + 1;
    }
}
````

### 迭代方式：

````java
class Solution {
    public int maxDepth(TreeNode root) {
        if(root == null)
            return 0;
        int depth = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        //可修改为 Queue<TreeNode> queue = new LinkedList<TreeNode>() {{ offer(root); }};
        //更加简洁
        while(queue.size() > 0){
            ++ depth;
            int size = queue.size();
            for(int i = 0 ;i < size; i++){
                TreeNode node = queue.poll();
                if(node.left != null)
                    queue.offer(node.left);
                if(node.right != null)
                    queue.offer(node.right);
            }
        }
        return depth;
    }
}
````

### 参考：

> https://leetcode-cn.com/problems/er-cha-shu-de-shen-du-lcof/

