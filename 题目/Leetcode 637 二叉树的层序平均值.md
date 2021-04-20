# 						[Leetcode 637二叉树的层平均值](https://leetcode-cn.com/problems/average-of-levels-in-binary-tree/)

### 数据结构定义：

````java
给定一个非空二叉树, 返回一个由每层节点平均值组成的数组。

 

示例 1：

输入：
    3
   / \
  9  20
    /  \
   15   7
输出：[3, 14.5, 11]
解释：
第 0 层的平均值是 3 ,  第1层是 14.5 , 第2层是 11 。因此返回 [3, 14.5, 11] 。
 
提示：

节点值的范围在32位有符号整数范围内。
    
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
````

### 迭代方式：

````java
class Solution {
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result =new ArrayList<>();
        Queue<TreeNode> queue =new LinkedList<>();
        TreeNode node; 
        if(root == null)
            return result;
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size(); 
            Double sum = 0.0;   
            for(int i = 0;i <size;i++){
                 node = queue.poll();
                 sum += node.val;
                 if(node.left != null)
                    queue.add(node.left);
                 if(node.right != null)
                    queue.add(node.right);
            }
            result.add(sum / size);
        }
        return result;
    }
}
````

### 递归方式：

````java
/*
*思路： 1:  运用深度优先遍历方式
	   2： result 保存某层的所有数之和，counts 记录该层的树节点个数
	   3： level 记录当前节点所在的层 。相同的层则将 总数 和 个数 放入对应下标层数的 result 和 counts
*/
class Solution {
    List<Double> result = new ArrayList<>();
    List<Integer> counts = new ArrayList<>();
    public List<Double> averageOfLevels(TreeNode root) {
        if(root == null)
            return result;
        dfs(root,0);
        for(int i = 0; i < result.size(); i++){
            result.set(i,result.get(i) / counts.get(i));
        }
        return result;
    }
    private void dfs(TreeNode root,int level){
        if(root == null)
            return;
        if(level >= result.size()){
            result.add((double)root.val);
            counts.add(1);
        }else{
            result.set(level,result.get(level) + root.val);
            counts.set(level,counts.get(level) + 1);
        }
        dfs(root.left,level + 1);
        dfs(root.right,level + 1);
    }
}
````

