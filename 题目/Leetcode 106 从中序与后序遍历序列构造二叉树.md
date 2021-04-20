# 						[Leetcode 106.从中序与后序遍历序列构造二叉树](https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/) 

### 数据结构定义：

````java

根据一棵树的中序遍历与后序遍历构造二叉树。

注意:
你可以假设树中没有重复的元素。

例如，给出

中序遍历 inorder = [9,3,15,20,7]
后序遍历 postorder = [9,15,7,20,3]
返回如下的二叉树：

    3
   / \
  9  20
    /  \
   15   7

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
````

### 递归写法：

````java
class Solution{
    public TreeNode buildTree(int[] inorder,int[] postorder){
        if(Objects.isNull(inorder) || inorder.length == 0
          || Objects.isNull(postorder) || postorder.length ==0){
            return null;
        }
        TreeNode root = new TreeNode(postorder[postorder.length-1]);
        int index = returnIndex(inorder,postorder[postorder.length - 1]);
        if(index > 0){
            buildTree(Arrays.copyOfRange(inorder,0,index),
                      Arrays.copyOfRange(postorder,0,index));
        }
        if(index != -1 && index < (postorder.length-1)){
            buildTree(Arrays.copyOrRange(inorder,index+1,inorder.length),
                     Arrays.copyOrRange(postorder,index,postorder.length -1));
        }
        return root;
            
    }
    
    private int returnIndex(int[] array,int num){
		for(int i = 0;i<array.length;i++){
            if(array[i] == num)
               return i;
        }
        return -1;
    }
}
````

### 运用反向前序遍历与反向后序遍历进行迭代：

````java
/*
*  参考：https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/solution/cong-zhong-xu-yu-hou-xu-bian-li-xu-lie-gou-zao-14/
*/
class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
		if(Objects.isNull(inorder) || inorder.length == 0
        || Objects.isNull(postorder) || postorder.length == 0){
            return null;
        }
        TreeNode root =new TreeNode(postorder[postorder.length -1]);
        Stack<TreeNode> stack = new Stack<>();
        int inorderIndex = inorder.length -1;
        stack.push(root);
        for(int i = postorder.length -2;i >= 0; i++){
            TreeNode node = stack.peek();
            int postorderValue = node.val;
            if(postorderValue != inorder[inorderIndex]){
                node.right = new TreeNode(postorderValue);
                stack.push(node.right);
            }else{
                while(!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]){
                    node = stack.pop();
                    inorderIndex --;
                }
                node.left = new TreeNode(postorderValue);
                stack.push(node.left);
            }
        }
        return root;
    }
}
````

### 另一种递归方案：

````java
class Solution {
    int[] inorder;
    int[] postorder;
    int index;
    Map<Integer,Integer> cacheMap = new HashMap<>();
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if(inorder == null || postorder ==null){
            return null;
        }
        this.inorder = inorder;
        this.postorder = postorder;
        index = postorder.length -1;
        for(int i=0;i<inorder.length;i++){
            cacheMap.put(inorder[i],i);
        }
        TreeNode root = helpRecursion(0,index);
        return root;
    }
    private TreeNode helpRecursion(int inLeft, int inRight){
        if(inLeft > inRight){
            return null;
        }

        TreeNode root = new TreeNode(postorder[index]);
        int inOrderIndex = cacheMap.get(postorder[index]);
        index --;
        root.right = helpRecursion(inOrderIndex + 1,inRight);
        root.left = helpRecursion(inLeft,inOrderIndex - 1);
        return root;
    }
}

/*
*	思路：创建哈希表来存储中序序列，提高性能	
*	定义一个递归函数，inLeft inRight分别为当前子树的左右边界，当inLeft>inRight 说明子树为空，返回null
*	每次选择后序遍历的最后一个节点作为根，所以需要先创建右子树再创建左子树
*/
````

