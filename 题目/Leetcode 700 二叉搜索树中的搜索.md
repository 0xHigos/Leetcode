# 						[Leetcode 700 二叉搜索树中的搜索](https://leetcode-cn.com/problems/search-in-a-binary-search-tree/)

### 题目定义：

````java
给定二叉搜索树（BST）的根节点和一个值。 你需要在BST中找到节点值等于给定值的节点。 返回以该节点为根的子树。 如果节点不存在，则返回 NULL。

例如，

给定二叉搜索树:

        4
       / \
      2   7
     / \
    1   3

和值: 2
你应该返回如下子树:

      2     
     / \   
    1   3
在上述示例中，如果要找的值是 5，但因为没有节点值为 5，我们应该返回 NULL。

````

### 递归方式：

````java
class Solution {
    private TreeNode node;
    public TreeNode searchBST(TreeNode root, int val) {
        if(root == null)
            return null;
        dfs(root,val);
        return node;
    }
    private void dfs(TreeNode root,int val){
        if(root == null)
            return;
        if(root.val == val)
            node = root;
        if(root.val > val)
            dfs(root.left,val);
        else 
            dfs(root.right,val);
    }
}
````

### 另一种简便遍历方式：

````java
class Solution {
    public TreeNode searchBST(TreeNode root, int val) {
        if(root == null)
            return null;
        return root.val == val ? root : root.val > val ? searchBST(root.left,val) : searchBST(root.right,val);
    }
}
````

### 迭代方式：

````java
class Solution {
    public TreeNode searchBST(TreeNode root, int val) {
        if(root == null)
            return null;
        while(root != null){
            if(root.val == val)
                return root;
            else if(root.val  > val)
                root = root.left;
            else 
                root = root.right;
        }
        return root;
    }
}
````
