### 题目定义：

````java
给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。

示例:

输入: [1,2,3,null,5,null,4]
输出: [1, 3, 4]
解释:

   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---

````



### 方式一(层序遍历)：

````java
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        Deque<TreeNode> queue = new ArrayDeque<>();
        List<Integer> res = new ArrayList<>();
        if(root == null)
            return res;
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++){
                TreeNode node = queue.removeLast();
                if(i == 0)
                    res.add(node.val);
                if(node.right != null)
                    queue.addFirst(node.right);
                if(node.left != null)
                    queue.addFirst(node.left);
            }
        }
        return res;
    }
}
````



### 方式二(深度优先遍历)：

````java
/*
* 思路： 每一层只会有一个节点放入list中，所以根据深度和list.size() 判断是否需要放入list
*		遍历方式 根-右-左
*/

class Solution {
    private List<Integer> ans = new ArrayList<>();
    public List<Integer> rightSideView(TreeNode root) {
        dfs(root,0);
        return ans;
    }
    private void dfs(TreeNode root,int depth){
        if(root == null)
            return;
        if(depth == ans.size())
            ans.add(root.val);
        dfs(root.right,depth + 1);
        dfs(root.left,depth + 1);
    }
}
````



