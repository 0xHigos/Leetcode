

### 题目定义：

````java
从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。

 

例如:
给定二叉树: [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
返回其层次遍历结果：

[
  [3],
  [9,20],
  [15,7]
]
 

提示：

节点总数 <= 1000
注意：本题与主站 102 题相同：https://leetcode-cn.com/problems/binary-tree-level-order-traversal/

````

### 递归方式：

````java
class Solution {
    private List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root == null)
            return ans;
        dfs(root,0);
        return ans;
    }
    private void dfs(TreeNode root,int level){
        if(ans.size() <= level){
            List<Integer> sub = new ArrayList<>();
            sub.add(root.val);
            ans.add(sub);
            // 可修改为 ans.add(new ArrayList<>(Collections.singletonList(root.val)));
        }else{
            ans.get(level).add(root.val);
        }
        if(root.left != null)
            dfs(root.left,level + 1);
        if(root.right != null)
            dfs(root.right,level + 1);
    }
}
````

### 迭代方式：

````java
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null)
            return ans;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(queue.size() > 0){
            int size = queue.size();
            List<Integer> sub = new ArrayList<>();
            for(int i = 0; i < size; ++i){
                TreeNode node = queue.poll();
                sub.add(node.val);
                if(node.left != null)
                    queue.offer(node.left);
                if(node.right != null)
                    queue.offer(node.right);
            }
            ans.add(sub);
        }
        return ans;
    }
}
````

### 参考：

> https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-ii-lcof/

