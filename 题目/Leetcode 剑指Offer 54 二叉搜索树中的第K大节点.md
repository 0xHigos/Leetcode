

### 题目定义：

````java
给定一棵二叉搜索树，请找出其中第k大的节点。

 

示例 1:

输入: root = [3,1,4,null,2], k = 1
   3
  / \
 1   4
  \
   2
输出: 4
示例 2:

输入: root = [5,3,6,2,4,null,null,1], k = 3
       5
      / \
     3   6
    / \
   2   4
  /
 1
输出: 4
 
限制：
1 ≤ k ≤ 二叉搜索树元素个数

````

### 先排序再获取值：

````java
class Solution {
    private List<Integer> ans = new ArrayList<>();
    public int kthLargest(TreeNode root, int k) {
        if(root == null)
            return 0;
        dfs(root);
        return ans.get(ans.size() - k);
    }
    private void dfs(TreeNode root){
        if(root == null)
            return;
        dfs(root.left);
        ans.add(root.val);
        dfs(root.right);
    }
}
````

### 递归方式：

````java
/*
* 思路：
*     求 “二叉搜索树第 kk 大的节点” 可转化为求 “此树的中序遍历倒序的第 k 个节点”
*	  定义 k 存储第k大的节点，定义 num 存储第k大的节点值
*     遍历方式： 右子树-根-左子树 ，每次获取最大的节点，并使k-1
*     当 k == 0 时，此时就是我们需要查找的第K大节点，返回num
*/
class Solution {
    private int k, num;
    public int kthLargest(TreeNode root, int k) {
        if(root == null)
            return 0;
        this.k = k;
        dfs(root);
        return num;
    }
    private void dfs(TreeNode root){
        if(root == null)
            return;
        dfs(root.right);
        if(--k == 0){
            num = root.val;
            return;
        }
        dfs(root.left);
        
    }
}
````

### 参考：

> https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof/

