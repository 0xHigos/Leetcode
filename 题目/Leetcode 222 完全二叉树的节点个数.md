### 题目定义：

````java
给你一棵 完全二叉树 的根节点 root ，求出该树的节点个数。

完全二叉树 的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~ 2h 个节点。
````

![img](https://assets.leetcode.com/uploads/2021/01/14/complete.jpg)

````java
输入：root = [1,2,3,4,5,6]
输出：6
    
示例 2：
输入：root = []
输出：0
    
示例 3：
输入：root = [1]
输出：1
````



### 方式一(深度优先遍历)：

````java
class Solution {
    private int count = 0;
    public int countNodes(TreeNode root) {
        dfs(root);
        return count;
    }
    private void dfs(TreeNode root){
        if(root == null)
            return;
        count ++;
        dfs(root.left);
        dfs(root.right);
    }
}
````



### 方式二(递归 + 位运算)：

````java
 /*
    思路：
    * （1）：left == right。这说明，左子树一定是满二叉树，因为节点已经填充到右子树了，
   	* 左子树必定已经填满了。
    * 所以左子树的节点总数我们可以直接得到，是 2^left - 1，加上当前这个 root 节点，则正好是 2^left。
    * 再对右子树进行递归统计。
    * （2）：left != right。说明此时最后一层不满，但倒数第二层已经满了，
    * 可以直接得到右子树的节点个数。同理，右子树节点 +root 节点，总数为 2^right。
    * 再对左子树进行递归查找。
    * */
class Solution {
    public int countNodes(TreeNode root) {
        if(root == null)
            return 0;
        int left = calLevel(root.left);
        int right = calLevel(root.right);
        if(left == right){
            return countNodes(root.right) + (1 << left);
        }else{
            return countNodes(root.left) + (1 << right);
        }
    }
    private int calLevel(TreeNode root){
        int level = 0;
        while(root != null){
            level ++;
            root = root.left;
        }
        return level;
    }
}
````



### 方式三(二分查找+位运算)：

````java
class Solution {
    public int countNodes(TreeNode root) {
        if(root == null)
            return 0;
        int level =calLevel(root);
        int low = 1 << level,high = (1 <<(level + 1)) - 1;
        while(low < high){
            int mid = (high - low + 1) / 2 + low;
            if(isExist(root,level,mid))
                low = mid;
            else 
                high = mid - 1; 
        }
        return low;
    }
    private int calLevel(TreeNode root){
        int level = 0;
        TreeNode node = root;
        while(node.left != null){
            level++;
            node = node.left;
        }
        return level;
    }

    private boolean isExist(TreeNode root,int level,int k){
        int bits = 1 << (level - 1);
        TreeNode node = root;
        while(node != null && bits > 0){
            if((bits & k) == 0)
                node = node.left;
            else 
                node = node.right;
            bits >>= 1;
        }
        return node != null;
    }
}
````



### 参考：

> https://leetcode-cn.com/problems/count-complete-tree-nodes/solution/chang-gui-jie-fa-he-ji-bai-100de-javajie-fa-by-xia/
>
> https://leetcode-cn.com/problems/count-complete-tree-nodes/solution/wan-quan-er-cha-shu-de-jie-dian-ge-shu-by-leetco-2/

