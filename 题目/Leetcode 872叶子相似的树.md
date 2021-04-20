# 						[Leetcode 872 叶子相似的树](https://leetcode-cn.com/problems/leaf-similar-trees/)

### 题目定义：

````java
请考虑一棵二叉树上所有的叶子，这些叶子的值按从左到右的顺序排列形成一个 叶值序列。
          3
    	 / \
        5   1
      / |   | \
     6  2   9  8
       / \
      7   4
举个例子，如上图所示，给定一棵叶值序列为 (6, 7, 4, 9, 8) 的树。
如果有两棵二叉树的叶值序列是相同，那么我们就认为它们是 叶相似 的。
如果给定的两个头结点分别为 root1 和 root2 的树是叶相似的，则返回 true；否则返回 false 。

示例 1：
          3                      3
    	 / \                    / \ 
        5   1                  5   1
      / |   | \               / |  | \
     6  2   9  8             6  7  4  2
       / \                           / \
      7   4                          9  8
输入：root1 = [3,5,1,6,2,9,8,null,null,7,4], root2 = [3,5,1,6,7,4,2,null,null,null,null,null,null,9,8]
输出：true

示例 2：
输入：root1 = [1], root2 = [1]
输出：true
    
示例 3：
输入：root1 = [1], root2 = [2]
输出：false
    
示例 4：
输入：root1 = [1,2], root2 = [2,2]
输出：true

示例 5：
       1                     1
      / \                   / \
     2   3                 3   2
输入：root1 = [1,2,3], root2 = [1,3,2]
输出：false
````

### 运用两个缓存：

````java
class Solution {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        if(root1 == null && root2 == null) 
            return true;
        if(root1 == null || root2 == null)
            return false;
        List<Integer> ans1 = new ArrayList<>();
        List<Integer> ans2 = new ArrayList<>();
        getLeaf(root1,ans1);
        getLeaf(root2,ans2);
        return isSameLeaf(ans1,ans2);
    }
    private void getLeaf(TreeNode root,List<Integer> ans){
        if(root == null)
            return;
        if(root.left == null && root.right == null)
            ans.add(root.val);
        getLeaf(root.left,ans);
        getLeaf(root.right,ans);
    }
    private boolean isSameLeaf(List<Integer> ans1,List<Integer> ans2){
        if(ans1.size() != ans2.size())
            return false;
        for(int i =0; i < ans1.size(); i++){
            if(ans1.get(i) != ans2.get(i))
                return false;
        }
        return true;
    }
}
````

