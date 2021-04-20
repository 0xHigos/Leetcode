# 						[Leetcode 590 N叉树的后序遍历](https://leetcode-cn.com/problems/n-ary-tree-postorder-traversal/)

### 数据结构定义：

````java
给定一个 N 叉树，返回其节点值的后序遍历。

例如，给定一个 3叉树 :
                  1
               /  |  \
              3   2   4
             / \ 
            5   6 
返回其后序遍历: [5,6,3,2,4,1].
    
说明: 递归法很简单，你可以使用迭代法完成此题吗?
/*
// Definition for a Node.
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
    private List<Integer> ans = new ArrayList<>();
    public List<Integer> postorder(Node root) {
        if(root == null)
            return ans;
        dfs(root);
        return ans;
    }
    private void dfs(Node root){
        if(root == null)
            return;
        for(Node child : root.children){
            dfs(child);
        }
        ans.add(root.val);
    }
}
````

### 迭代方式方式：

````java
class Solution {
    public List<Integer> postorder(Node root) {
        LinkedList<Integer> result = new LinkedList<>();
        Deque<Node> stack = new LinkedList<>();
        if(root == null){
            return result;
        }
        stack.addFirst(root);
        while(!stack.isEmpty()){
            Node node = stack.poll();
            result.addFirst(node.val);
            for(int i = 0; i< node.children.size();i++){
                stack.addFirst(node.children.get(i));
            }
        }
        return result;
    }
}
````

### 广度优先遍历方式：

````java

````

### 参考：