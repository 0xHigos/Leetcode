# 						[Leetcode 二叉树的最小深度 ](https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/)

### 数据结构定义：

````java
给定一个二叉树，找出其最小深度。

最小深度是从根节点到最近叶子节点的最短路径上的节点数量。

说明：叶子节点是指没有子节点的节点。
    
输入：root = [3,9,20,null,null,15,7]
输出：2
    
输入：root = [2,null,3,null,4,null,5,null,6]
输出：5
````

### 普通深度递归写法：

````java
class Solution {
    private int depth = Integer.MAX_VALUE;
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfsDepth(root, 1);
        return depth;
    }

    private void dfsDepth(TreeNode root, int length) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            depth = Math.min(depth, length);
            return;
        }
        dfsDepth(root.left,  length + 1);
        dfsDepth(root.right,  length + 1);
    }
}
````

### 另一种深度遍历方式：

````java
class Solution {
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        else if(root.left == null) return minDepth(root.right) + 1;
        else if(root.right == null) return minDepth(root.left) + 1;
        else return Math.min(minDepth(root.left),minDepth(root.right)) + 1;
    }
}
````

### 广度优先遍历

````java
/*
* 思路： 定义QueueNode类，保存TreeNode和对应的depth
*/
class Solution {
    class QueueNode{
        private TreeNode root;
        private int depth;
        QueueNode(TreeNode root,int depth){
            this.root = root;
            this.depth = depth;
        }
        public TreeNode getTreeNode(){
            return root;
        }
        public int getDepth(){
            return depth;
        }
    }
    public int minDepth(TreeNode root) {
            if(root == null){
                return 0;
            }
            Queue<QueueNode> queue = new LinkedList<>();
            queue.offer(new QueueNode(root,1));
            while(!queue.isEmpty()){
                QueueNode node = queue.poll();
                int depth = node.getDepth();
                TreeNode treeNode = node.getTreeNode();
                if(treeNode.left == null && treeNode.right == null){
                    return depth;
                }
                if(treeNode.left != null){
                    queue.offer(new QueueNode(treeNode.left,depth + 1));
                }
                if(treeNode.right != null){
                    queue.offer(new QueueNode(treeNode.right,depth + 1));
                }
            }
            return 0;
    }
}
````
