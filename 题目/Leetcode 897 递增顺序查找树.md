### 题目定义：

````java
给你一个树，请你 按中序遍历 重新排列树，使树中最左边的结点现在是树的根，并且每个结点没有左子结点，只有一个右子结点。

 

示例 ：

输入：[5,3,6,2,4,null,8,1,null,null,null,7,9]

       5
      / \
    3    6
   / \    \
  2   4    8
 /        / \ 
1        7   9

输出：[1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]

 1
  \
   2
    \
     3
      \
       4
        \
         5
          \
           6
            \
             7
              \
               8
                \
                 9  
 

提示：

给定树中的结点数介于 1 和 100 之间。
每个结点都有一个从 0 到 1000 范围内的唯一整数值。

````

### 中序遍历 + 构造新的树：

````java
class Solution {
    private List<TreeNode> cache = new ArrayList<>();
    public TreeNode increasingBST(TreeNode root) {
        if(root == null)
            return null;
        dfs(root);
        return getNewTreeNode();
    }

    private void dfs(TreeNode root){
        if(root == null)
            return;
        dfs(root.left);
        cache.add(root);
        dfs(root.right);
    }

    private TreeNode getNewTreeNode(){
        TreeNode result = new TreeNode(-1);
        TreeNode temp = result;
        for(TreeNode node : cache){
            temp.right =new TreeNode(node.val);
            temp = temp.right;
        }
        return result.right;
    }
}
````

### 中序遍历 + 尾插法：

````java
class Solution {
    private TreeNode tail;
    public TreeNode increasingBST(TreeNode root) {
        if(root == null)
            return null;
        TreeNode node = new TreeNode(-1);   //定义树的头节点
        tail = node;  //定义尾指针
        setArrangeTreeNode(root);
        return node.right;
    }
    
    private void setArrangeTreeNode(TreeNode root){
        if(root == null)
            return;
        setArrangeTreeNode(root.left);
        root.left = null;
        tail.right = root;
        tail = root;
        setArrangeTreeNode(root.right);
    }
}
````

### 参考：

> https://leetcode-cn.com/problems/increasing-order-search-tree/

