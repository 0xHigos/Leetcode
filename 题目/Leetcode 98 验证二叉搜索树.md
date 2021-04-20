

### 题目定义：

````java
给定一个二叉树，判断其是否是一个有效的二叉搜索树。

假设一个二叉搜索树具有如下特征：

节点的左子树只包含小于当前节点的数。
节点的右子树只包含大于当前节点的数。
所有左子树和右子树自身必须也是二叉搜索树。
示例 1:

输入:
    2
   / \
  1   3
输出: true
示例 2:

输入:
    5
   / \
  1   4
     / \
    3   6
输出: false
解释: 输入为: [5,1,4,null,null,3,6]。
     根节点的值为 5 ，但是其右子节点值为 4 。

````



### 方式一(递归判断)：

````java
class Solution {
    public boolean isValidBST(TreeNode root) {
        if(root == null)
            return true;
        return recursion(root,null,null);
    }
    private boolean recursion(TreeNode root,Integer lower,Integer upper){
        if(root == null)   
            return true;
        if((lower != null && root.val <= lower) || (upper != null && root.val >= upper))
             return false;
        return recursion(root.left,lower,root.val) 
            && recursion(root.right,root.val,upper);
    }

}
````



### 方式二(中序遍历)：

````java
class Solution {
    public boolean isValidBST(TreeNode root) {
        if(root == null)
            return true;
        Deque<TreeNode> stack = new ArrayDeque<>();
        double prev = - Double.MAX_VALUE;
        while(!stack.isEmpty() || root != null){
           while(root != null){
               stack.push(root);
               root = root.left;
           }
           root = stack.pop();
           if(root.val <= prev)
             return false;
           prev = root.val; 
           root = root.right;
        }
        return true;
    }
  
````



### 方式三()：

````java

````



### 参考：

> 

