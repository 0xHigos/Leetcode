### 题目定义：

````java
给定一个二叉树，原地将它展开为一个单链表。

 

例如，给定二叉树

    1
   / \
  2   5
 / \   \
3   4   6
将其展开为：

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
````



### 方式一(前序遍历-迭代)：

````java
/*
* 思路：
    将左子树插入到右子树的地方
    将原来的右子树接到左子树的最右边节点
    考虑新的右子树的根节点，一直重复上边的过程，直到新的右子树为 null
*/
class Solution {
    public void flatten(TreeNode root) {
        while(root != null){
            if(root.left != null){
                TreeNode left = root.left;
                TreeNode right = root.right;
                root.right = left;
                root.left = null;
                TreeNode node = root;
                while(node.right != null){
                    node = node.right;
                }
                node.right = right;
            }
            root = root.right;
        }
    }
}
````



### 方式二(前序遍历-递归)：

````java
class Solution {
    public void flatten(TreeNode root) {
        if(root == null)
            return;
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.right = left;
        root.left = null;
        TreeNode node = root;
        while(node.right != null)
            node = node.right;
        node.right =right;
        flatten(root.right);
    }
}
````



### 方式三(变形的后续遍历-迭代)：

````java
class Solution {
    public void flatten(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode pre = null;
        while(root != null || !stack.isEmpty()){
            while(root != null){
                stack.push(root);
                root = root.right;
            }
            root = stack.peek();
            if(root.left == null || root.left == pre){
                stack.pop();
                root.left = null;
                root.right = pre;
                pre = root;
                root = null;
            }else{
                root = root.left;
            }
        }
    }
}
````



### 方式四(变形的后续遍历-递归)：

````java
class Solution {
    private TreeNode pre; 
    public void flatten(TreeNode root) {
        if(root == null)
            return;
        flatten(root.right);
        flatten(root.left);
        root.right = pre; 
        root.left = null;
        pre = root;
    }
}
````





### 方式五(前序遍历-栈)：

````java
class Solution {
    public void flatten(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode pre = null;
        if(root == null)
            return;
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            if(pre != null){
                pre.right = node;
                pre.left = null;
            }
            if(node.right != null)
                stack.push(node.right);
            if(node.left != null)
                stack.push(node.left);
            pre = node;
        }
    }
}
````



### 方式六(morris遍历)：

````java
class Solution {
    public void flatten(TreeNode root) {
        if (root == null){
            return ;
        }
        TreeNode curr = root;
        while(curr != null){
            TreeNode mostRight = curr.left;
            if (mostRight != null){
                while(mostRight.right != null && mostRight.right != curr){
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null){
                    mostRight.right = curr;
                    curr = curr.left; 
                    continue;
                }else{
                    //在第二次来到该节点的时候，调整指针。
                    mostRight.right = null;
                    TreeNode right = curr.right;
                    curr.right = curr.left;
                    curr.left = null;
                    while(curr.right != null){
                        curr = curr.right;
                    }
                    curr.right = right;
                }
            }
            curr = curr.right;
        }
    }
}
````



### 方式七(第二种morris遍历)：

````java
class Solution {
    public void flatten(TreeNode root) {
        TreeNode cur = root;
        while(cur != null){
            if(cur.left == null){
                cur = cur.right;
            }else{
                TreeNode pre = cur.left;
                while(pre.right != null && pre.right != cur)
                    pre = pre.right;
                if(pre.right == null){
                    pre.right = cur;
                    cur = cur.left;
                }else{
                    pre.right = null;
                    TreeNode right = cur.right;
                    cur.right = cur.left;
                    cur.left = null;
                    while(cur.right != null)
                        cur = cur.right;
                    cur.right = right;
                }
            }
        }
    }
}
````



### 参考：

> https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--26/

