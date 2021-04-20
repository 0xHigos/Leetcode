### 题目定义：

````java
给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。

例如：
给定二叉树 [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
返回锯齿形层序遍历如下：

[
  [3],
  [20,9],
  [15,7]
]

````



### 方式一(广度优先遍历)：

````java
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null)
            return ans;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        boolean leftOrRight = true;
        while(!queue.isEmpty()){
            LinkedList<Integer> level = new LinkedList<>();
            int size  = queue.size();
            for(int i = 0; i < size; i++){
                TreeNode node = queue.poll();
                    if(leftOrRight)
                        level.addLast(node.val);
                    else
                        level.addFirst(node.val);
                    if(node.left != null)
                        queue.offer(node.left);
                    if(node.right != null)
                        queue.offer(node.right);
            }
            leftOrRight = !leftOrRight;
            ans.add(level);
        }
        return ans;
    }
}
````



### 方式二(深度优先遍历):

````java
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        travel(ans,root,0);
        return ans;
    }
    private void travel(List<List<Integer>> ans,TreeNode root,int level){
        if(root == null)
            return;
        if(ans.size() <= level){
            ans.add(new ArrayList<Integer>(){{add(root.val);}});
        }else{
            List<Integer> temp = ans.get(level);
            if(level % 2 == 0)
                temp.add(root.val);
            else
                temp.add(0,root.val);
        }
        travel(ans,root.left,level + 1);
        travel(ans,root.right,level + 1);
    }
}
````



### 参考：

> https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/

