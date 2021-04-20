

### 题目定义：

````java
请完成一个函数，输入一个二叉树，该函数输出它的镜像。

例如输入：

     4
   /   \
  2     7
 / \   / \
1   3 6   9
    
镜像输出：

     4
   /   \
  7     2
 / \   / \
9   6 3   1

 
示例 1：
输入：root = [4,2,7,1,3,6,9]
输出：[4,7,2,9,6,3,1]
 

限制：
 0 <= 节点个数 <= 1000
 注意：本题与主站 226 题相同：https://leetcode-cn.com/problems/invert-binary-tree/
````

### 递归方式：

````java
class Solution {
    public TreeNode mirrorTree(TreeNode root) {
        if(root == null)
            return null;
        TreeNode temp = mirrorTree(root.left);
        root.left = mirrorTree(root.right);
        root.right = temp;
        
        return root;
    }
}
````

### 迭代方式：

````java
class Solution {
    public TreeNode mirrorTree(TreeNode root) {
        if(root == null)
            return null;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(queue.size() > 0){
            TreeNode node = queue.poll();
            swap(node);
            if(node.left != null)
                queue.offer(node.left);
            if(node.right != null)
                queue.offer(node.right);
        }
        return root;
    }
    private void swap(TreeNode node){
        TreeNode temp = node.left;
        node.left = node.right;
        node.right = temp;
    }
}
````

### 参考：

> https://leetcode-cn.com/problems/er-cha-shu-de-jing-xiang-lcof/submissions/

