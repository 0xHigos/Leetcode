# 						[Leetcode 559 N叉树的最大深度](https://leetcode-cn.com/problems/maximum-depth-of-n-ary-tree/submissions/)

### 数据结构定义：

````java
给定一个 N 叉树，找到其最大深度。

最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。

N 叉树输入按层序遍历序列化表示，每组子节点由空值分隔（请参见示例）。
示例 1：
    				  1
    				/ | \
                   3  2  4
                  / \
                 5   6
输入：root = [1,null,3,2,4,null,5,6]
输出：3
    
/*
Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/
````

### 递归方式：

````java
class Solution {
    public int maxDepth(Node root) {
        if(root == null){
            return 0;
        }
        int[] depthes = new int[root.children.size()];
        for(int i = 0;i< root.children.size(); ++i){
            depthes[i] = maxDepth(root.children.get(i));
        }
        return depthes.length > 0 ? Arrays.stream(depthes).max().getAsInt() + 1 : 1;
    }
}
````

### 递归的简洁方式：

````java
class Solution {
    public int maxDepth(Node root) {
        if(root == null){
            return 0;
        }
        int depth = 1;
        for(Node children :root.children){
            depth =  Math.max(depth,maxDepth(children) + 1);
        }
        return depth;
    }
}
````

### 迭代遍历：

````java
class Solution {
    public int maxDepth(Node root) {
        Queue<Pair<Node,Integer>> stack = new LinkedList<>();
        if(root != null){
            stack.offer(new Pair(root,1));
        }
        int max = 0;
        while(!stack.isEmpty()){
            Pair<Node,Integer> node = stack.poll();
            int value = node.getValue();
            root =  node.getKey();
            if(root != null){
                max = Math.max(max,value);
                for(Node temp : root.children){
                    stack.offer(new Pair(temp,value + 1));
                 }
            }
        }
        return max;
    }
}
````

