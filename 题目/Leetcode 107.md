# 						[Leetcode 107 二叉树的层序遍历 II ]( https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii/ )

### 数据结构定义：

````java
给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）

例如：
给定二叉树 [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
返回其自底向上的层次遍历为：

[
  [15,7],
  [9,20],
  [3]
]

````

### 广度优先遍历写法：

````java
/*
* 思路： 定义一个队列存储某一行的所有节点
*/
class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        Queue<TreeNode> queue =new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            List<Integer> level = new ArrayList<>();
            int size = queue.size();
            for(int i = 0; i < size; i++){
                TreeNode node =queue.poll();
                level.add(node.val);
                if(node.left != null){
                    queue.offer(node.left);
                }
                if(node.right != null){
                    queue.offer(node.right);
                }
            }
            result.add(level);
        }
        Collections.reverse(result);
        return result;
    }
}
````

### 广度优先遍历的另一种写法：

````java
/*
* 思路： 使用linkedList的特性，每次数据插入到最前方
*/
class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> result = new LinkedList<>();
        if(root == null){
            return result;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            List<Integer> level = new ArrayList<>();
            int size = queue.size();
            for(int i =0;i<size;i++){
                TreeNode node = queue.poll();
                level.add(node.val);
                if(node.left != null){
                    queue.offer(node.left);
                }
                if(node.right != null){
                    queue.offer(node.right);
                }
            }
            result.add(0, level);
        }
        return result;
    }
}
````

### 深度优先遍历：

````java
/*
* 思路： 定义一个index记录哪层遍历的节点
*/
class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        dfs(root,result,1);
        Collections.reverse(result);
        return result;
    }

    private void dfs(TreeNode root, List<List<Integer>> result, int index) {
        if(root == null){
            return;
        }
        if(index - result.size() > 0){
            result.add(new ArrayList<>());
        }
        result.get(index -1).add(root.val);
        dfs(root.left,result,index + 1);
        dfs(root.right,result,index + 1);
    }
}

````

