### 题目定义：

````java
给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。

说明: 叶子节点是指没有子节点的节点。

示例:
给定如下二叉树，以及目标和 sum = 22，

              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
返回:

[
   [5,4,11,2],
   [5,8,4,5]
]

````



### 方式一(回溯)：

````java
class Solution {
    private List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if(root == null)
            return ans;
        dfs(new ArrayList<>(),root,sum);
        return ans;
    }
    private void dfs(List<Integer> list,TreeNode root,int remaining){
        if(root == null)
            return;
        list.add(root.val);
        if(root.left == null && root.right == null && remaining - root.val == 0){
            List<Integer> dest = new ArrayList<>();
            Collections.addAll(dest, new Integer[list.size()]);
            Collections.copy(dest,list);
            ans.add(dest);
            return;
        }
        dfs(list,root.left,remaining - root.val);
        dfs(list,root.right,remaining - root.val);
        list.remove(list.size() - 1);
    }
}
````



### 方式二(深度优先遍历)：

````java
class Solution {
    List<List<Integer>> ans = new LinkedList<>();
    Deque<Integer> stack = new LinkedList<>();
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        dfs(root,targetSum);
        return ans;
    }
    private void dfs(TreeNode root,int targetSum){
        if(root == null)
            return;
        stack.offerLast(root.val);
        targetSum -= root.val;
        if(root.left == null && root.right == null && targetSum == 0){
            ans.add(new LinkedList<>(stack));
        }
        dfs(root.left,targetSum);
        dfs(root.right,targetSum);
        stack.pollLast();
    }
}
````



### 方式三(广度优先遍历)：

````java
class Solution {
    List<List<Integer>> ans = new ArrayList<>();
    Map<TreeNode,TreeNode> cache = new HashMap<>();
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        if(root == null)
            return ans;
        
        Queue<TreeNode> queueNode = new ArrayDeque<>();
        Queue<Integer> queueParentSum = new ArrayDeque<>();
        queueNode.offer(root);
        queueParentSum.offer(0);
        while(!queueNode.isEmpty()){
            TreeNode node = queueNode.poll();
            int ret = node.val + queueParentSum.poll();
            if(node.left == null && node.right == null){
                if(ret  == targetSum){
                    getPath(node);
                }
            }else{
                if(node.left != null){
                    cache.put(node.left,node);
                    queueNode.offer(node.left);
                    queueParentSum.offer(ret);
                }
                if(node.right != null){
                    cache.put(node.right,node);
                    queueNode.offer(node.right);
                    queueParentSum.offer(ret);
                }
            }
        }
        return ans;
    }

    private void getPath(TreeNode node){
        List<Integer> temp = new ArrayList<>();
        while(node != null){
            temp.add(node.val);
            node = cache.get(node);
        }
        Collections.reverse(temp);
        ans.add(temp);
    }
}
````



### 参考：

> https://leetcode-cn.com/problems/path-sum-ii/solution/lu-jing-zong-he-ii-by-leetcode-solution/

