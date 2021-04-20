##                           [二叉树的前序遍历](https://leetcode-cn.com/problems/binary-tree-preorder-traversal/)(Leetcode 144)

#### 数据结构定义：

````java

 // Definition for a binary tree node.
  public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
````



#### 递归写法：

````java
class Solution {
    public List<Integer> result = new ArrayList<>();
    public List<Integer> preorderTraversal(TreeNode root) {
        if(root == null)
            return  new ArrayList<>();
        result.add(root.val);
        if(root.left != null)
            preorderTraversal(root.left);
        if(root.right != null)
        preorderTraversal(root.right);

        return result;
    }
}
````

#### 普通迭代：

````java
class Solution {
    
    public List<Integer> preorderTraversal(TreeNode root) {
        if(root == null)
            return new ArrayList<Integer>(); 
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode node =stack.pop();
            result.add(node.val);
            if(node.right != null){
                stack.push(node.right);
            }
            if(node.left != null){
                stack.push(node.left);
            }
        } 
        return result;
    }
}
````

#### 只保存右节点的迭代

````java
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null)
            return result;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node =root;
        while(node != null){
            result.add(node.val);
            if(node.right != null){
                stack.push(node.right);
            }
            if(node.left != null){
                node = node.left;
            }else{
                if(!stack.isEmpty()){
                    node = stack.pop();
                }else{
                    break;
                }
            }
        }
        return result;
    }
}
````

#### 莫里斯遍历

````java
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null)
            return result;
        TreeNode node = root;
        while(node != null){
            if(node.left == null){
                result.add(node.val);
                node = node.right;
            }else{
                TreeNode pre = node.left;
                while(pre.right != null && pre.right != node) // 找到左子树下的最右节点
                    pre = pre.right;
                    
                if(pre.right == null){
                    result.add(node.val);
                    pre.right = node;
                    node = node.left;
                }else{
                    pre.right = null;
                    node = node.right;
                }
            }
        }
        return result;
    }
}
````

#### 莫里斯遍历请参考：

##### [二叉树中序遍历的三种方式](https://www.cnblogs.com/CodingXu-jie/p/13531475.html)

