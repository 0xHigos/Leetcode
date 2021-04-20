

### 题目定义：

````java
如果二叉树每个节点都具有相同的值，那么该二叉树就是单值二叉树。
只有给定的树是单值二叉树时，才返回 true；否则返回 false。
    
示例 1：
                            1
                          /  \
                         1     1
                        / \     \
                       1   1     1
输入：[1,1,1,1,1,null,1]
输出：true
    
示例 2：
                            2
                          /  \
                         2    2
                        / \   
                       5   2 
输入：[2,2,2,5,2]
输出：false

    
提示：
给定树的节点数范围是 [1, 100]。
每个节点的值都是整数，范围为 [0, 99]。
````

### 递归方式：

````java
class Solution {
    public boolean isUnivalTree(TreeNode root) {
        if(root == null)
            return true;
        return dfs(root,root.val);
    }
    private boolean dfs(TreeNode root,int value){
        if(root == null)
            return true;
        if(root.val != value)
            return false;
        return dfs(root.left,value) && dfs(root.right,value);
    }
}
````

### 迭代方式：

````java
class Solution {
    public boolean isUnivalTree(TreeNode root) {
        if(root == null)
            return true;
        Queue<TreeNode> queue =new LinkedList<>();
        int value = root.val;
        queue.offer(root);
        while(queue.size() > 0){
            TreeNode node = queue.poll();
            if(node.val != value)
                return false;
            if(node.left != null)
                queue.offer(node.left);
            if(node.right != null)
                queue.offer(node.right);
        }
        return true;
    }
}
````

### 参考：

> https://leetcode-cn.com/problems/univalued-binary-tree/submissions/

