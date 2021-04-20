

### 题目定义：

````java
请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。

例如，二叉树 [1,2,2,3,4,4,3] 是对称的。

    1
   / \
  2   2
 / \ / \
3  4 4  3
但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:

    1
   / \
  2   2
   \   \
   3    3

示例 1：
输入：root = [1,2,2,3,4,4,3]
输出：true
        
示例 2：
输入：root = [1,2,2,null,3,null,3]
输出：false
 
限制：

  0 <= 节点个数 <= 1000
  注意：本题与主站 101 题相同：https://leetcode-cn.com/problems/symmetric-tree/

````

### 递归方式(自顶向下)：

````java
class Solution {
    public boolean isSymmetric(TreeNode root) {
        if(root == null)
            return true;
        return dfs(root.left,root.right);
    }
    private boolean dfs(TreeNode left,TreeNode right){
        if(left == null && right == null)
            return true;
        if(left == null || right == null || left.val != right.val)
            return false;
        return dfs(left.left,right.right) && dfs(left.right,right.left);
    }
}
````

### 迭代方式：

````java
class Solution {
    public boolean isSymmetric(TreeNode root) {
        if(root == null)
            return true;
        Deque<TreeNode> deque = new LinkedList<>();
        deque.offer(root.left);
        deque.offer(root.right);
        while(deque.size() > 0){
            int size = deque.size();
            for(int i = 0; i < size; i += 2){
                TreeNode node1 = deque.poll();
                TreeNode node2 = deque.poll();
                if(node1 ==null && node2 == null)
                    continue;
                if(node1 == null || node2 == null || node1.val != node2.val)
                    return false;
                deque.offer(node1.left);
                deque.offer(node2.right);
                deque.offer(node1.right);
                deque.offer(node2.left);
            }
        }
        return true;
    } 
}
````

### 参考：

> https://leetcode-cn.com/problems/dui-cheng-de-er-cha-shu-lcof/

