# 						[Leetcode 二叉树中第二小的节点](https://leetcode-cn.com/problems/second-minimum-node-in-a-binary-tree/)

### 题目定义：

````java
给定一个非空特殊的二叉树，每个节点都是正数，并且每个节点的子节点数量只能为 2 或 0。如果一个节点有两个子节点的话，那么该节点的值等于两个子节点中较小的一个。

更正式地说，root.val = min(root.left.val, root.right.val) 总成立。

给出这样的一个二叉树，你需要输出所有节点中的第二小的值。如果第二小的值不存在的话，输出 -1 。

示例 1：
                 2
    			/ \
               2   5
                  / \
                 5   7 
    
输入：root = [2,2,5,null,null,5,7]
输出：5
解释：最小的值是 2 ，第二小的值是 5 。  
    
示例 2：
                2
               / \ 
              2   2

输入：root = [2,2,2]
输出：-1
解释：最小的值是 2, 但是不存在第二小的值。

````

### 使用set 保存所有节点值并排序：

````java
class Solution {
    public int findSecondMinimumValue(TreeNode root) {
        if(root == null)
            return -1;
        Set<Integer> set = new HashSet<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(queue.size() > 0){
            TreeNode node = queue.poll();
            set.add(node.val);
            if(node.left != null)
                queue.offer(node.left);
            if(node.right != null)
                queue.offer(node.right);
        }
        List<Integer> result = new ArrayList<>(set);
        Collections.sort(result);
        if(result.size() == 1)
            return -1;
        else 
            return result.get(1);
    }
}
````

### 使用min 保存最小的节点：

````java
/*
* 思路： 使用min 保存根节点的值(最小值)，递归遍历所有节点，当递归到的节点大于最小值，
*	    则返回该值并与其他节点对比，若两个节点都大于min 则取最小的那个值
*       若 有节点小于 min(遍历到空节点，会返回 -1) ，则取所有值中最大的那个
*/
class Solution {
    public int findSecondMinimumValue(TreeNode root) {
        if(root == null)
            return -1;
        return dfs(root,root.val);
    }
    private int dfs(TreeNode root,int min){
        if(root == null)
            return -1;
        if(root.val > min)
            return root.val;
        int left = dfs(root.left,min);
        int right = dfs(root.right,min);
        if(left > min && right > min)
            return Math.min(left,right);
        else 
            return Math.max(left,right);
    }
}
````

### 