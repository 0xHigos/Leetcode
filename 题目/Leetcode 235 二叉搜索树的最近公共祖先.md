# 						[Leetcode 235 二叉树的最近公共祖先](https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree/)

### 数据结构定义：

````java
给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。

百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”

示例 1:

输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
输出: 6 
解释: 节点 2 和节点 8 的最近公共祖先是 6。
示例 2:

输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
输出: 2
解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。
 
说明:
	所有节点的值都是唯一的。
	p、q 为不同节点且均存在于给定的二叉搜索树中
````

### 递归写法：

````java
/*
*思路：因为是二叉搜索树，树中的节点有顺序，当搜寻到的结果 root 在对比两个节点时刚好符合 大于某一个节点且小于另一个节点时，表名改节点就是二叉树的最近公共祖先节点 下方的一次遍历迭代思路一样
*/
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root.val > p.val && root.val > q.val)
            return lowestCommonAncestor(root.left,p,q);
        else if(root.val < p.val && root.val < q.val) 
            return lowestCommonAncestor(root.right,p,q);
        else return root;
    }
}
````

### 一次遍历迭代：

````java
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
       TreeNode node = root;
       while(true){
           if(node.val > p.val && node.val > q.val)
               node =node.left;
           else if(node.val < p.val && node.val < q.val)
               node = node.right;
           else
               break;
       }
       return node;
    }
}
````

### 增加缓存的迭代：

````java
public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> pathP = getPath(root, p);
        List<TreeNode> pathQ = getPath(root, q);
        TreeNode ancestor = null;
        for (int i = 0; i < pathP.size() && i < pathQ.size(); i++) {
            if(pathP.get(i) == pathQ.get(i))
                ancestor = pathP.get(i);
            else return ancestor;
        }
        return ancestor;

    }
    private List<TreeNode> getPath(TreeNode root,TreeNode target){
        List<TreeNode> result = new ArrayList<>();
        TreeNode node = root;
        while(node != target){
            result.add(node);
            if(node.val < target.val)
                node = node.right;
            else
                node = node.left;
        }
        result.add(target);
        return result;
    }
````

