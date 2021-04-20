# 						[Leetcode 117. 填充每个节点的下一个右侧节点指针 II]( https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node-ii/solution/)

### 数据结构定义：

````java
给定一个完美二叉树，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：

struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}
填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。

初始状态下，所有 next 指针都被设置为 NULL。  

/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/
````



### 递归写法：

````java

````

### 双指针递归：

````java

````

### 运用队列进行迭代

````java

class Solution {
    public Node connect(Node root) {
        if(root == null){
            return null;
        }
        LinkedList<Node> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0;i < size; i++){
                Node temp = queue.poll();
                if(i < size -1){
                    temp.next = queue.peek();
                }
                if(temp.left != null){
                    queue.offer(temp.left);
                }
                if(temp.right != null){
                    queue.offer(temp.right);
                }
            }
        }
        return root;
    }
}
````

