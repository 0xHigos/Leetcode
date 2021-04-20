

### 题目定义：

````java
给定一个有序整数数组，元素各不相同且按升序排列，编写一个算法，创建一棵高度最小的二叉搜索树。

示例:
给定有序数组: [-10,-3,0,5,9],

一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：

          0 
         / \ 
       -3   9 
       /   / 
     -10  5 
````



### 递归方式：

````java
class Solution { 
    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums == null)
            return null;
        return dfs(nums,0,nums.length-1);
    }
    private TreeNode dfs(int[] nums,int left,int right){
        if(left > right)
            return null;
        int mid =left + ((right - left) >> 1);
        TreeNode root = new TreeNode(nums[mid]);
        root.left = dfs(nums,left,mid-1);
        root.right =dfs(nums,mid+1,right);
        return root;
    }
}
````


