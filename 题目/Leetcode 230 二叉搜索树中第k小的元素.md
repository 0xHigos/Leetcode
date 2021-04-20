### 题目定义：

````java
给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。

示例 1：
````

![img](https://assets.leetcode.com/uploads/2021/01/28/kthtree1.jpg)

````java
输入：root = [3,1,4,null,2], k = 1
输出：1
    
    
示例 2：
````

![img](https://assets.leetcode.com/uploads/2021/01/28/kthtree2.jpg)

````java
输入：root = [5,3,6,2,4,null,null,1], k = 3
输出：3
````

### 方式一(运用缓存)：

````java
class Solution {
    private int count = 0;
    private Map<Integer, Integer> map = new HashMap<>();
    public int kthSmallest(TreeNode root, int k) {
        getMap(root);
        return map.get(k);
    }

    private void getMap(TreeNode root) {
        if(root == null)
            return;
        getMap(root.left);
        map.put(++count,root.val);
        getMap(root.right);
    }
}
````



### 方式二(深度优先遍历)：

````java
class Solution {
    private int count = 0;
    public int kthSmallest(TreeNode root, int k) {
        if(root == null)
            return -1;
        int left = kthSmallest(root.left,k);
        count ++;
        if(left == -1 && count == k)
            return root.val;
        int right = kthSmallest(root.right,k);
        return left != -1 ? left : right;
    }
}
````



### 方式三(迭代遍历)：

````java
class Solution {
    public int kthSmallest(TreeNode root, int k) {
        Deque<TreeNode> stack = new LinkedList<>();
        while(true){
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.poll();
            if(--k == 0)
                return root.val;
            root = root.right;
        }
    }
}
````



### 参考：

> https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst/solution/er-cha-sou-suo-shu-zhong-di-kxiao-de-yuan-su-by-le/

