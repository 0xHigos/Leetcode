# 						[Leetcode 105 ](  )

### 数据结构定义：

````java
根据一棵树的前序遍历与中序遍历构造二叉树。

注意:
你可以假设树中没有重复的元素。

例如，给出

前序遍历 preorder = [3,9,20,15,7]
中序遍历 inorder = [9,3,15,20,7]
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
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(Objects.isNull(preorder) || preorder.length == 0
        || Objects.isNull(inorder) || inorder.length == 0){
            return null;
        }
        TreeNode root  = new TreeNode(preorder[0]);
        int index = returnIndex(inorder,preorder[0]);
        if(index > 0){
            root.left = buildTree(Arrays.copyOfRange(preorder,1,index+1),
            Arrays.copyOfRange(inorder,0,index));
        }
        if(index != -1 && index < inorder.length -1){
            root.right = 
                buildTree(Arrays.copyOfRange(preorder,index+1,preorder.length),
                    Arrays.copyOfRange(inorder,index+1,inorder.length));
        }
        return root;
    }

    private int returnIndex(int[] array,int num){
        for(int i =0;i<array.length;i++){
            if(array[i] == num){
                return i;
            }
        }
        return -1;
    }
}
````

### 加缓存的递归写法：

````java
class Solution {
    /*
    * map 做一个缓存
    */
    private Map<Integer,Integer> map =new HashMap<>();
    private int[] preorder ;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder == null || inorder == null || preorder.length <= 0 || inorder.length <=0){
            return null;
        }
        this.preorder =preorder;
        for(int i= 0; i<inorder.length;i++){
            map.put(inorder[i],i);
        }
        return helpRecursion(0,preorder.length-1,0,preorder.length-1);
    }
    
    private TreeNode helpRecursion(int preStart,int preEnd,int inStart,int inEnd){
            if(preStart > preEnd || inStart > inEnd){
                return null;
            }
            TreeNode root = new TreeNode(preorder[preStart]);
            int index = map.get(preorder[preStart]); 
            int leftSum = index - inStart;
            root.left = helpRecursion(preStart+1,preStart+leftSum,inStart,inStart+leftSum);
            root.right = helpRecursion(preStart+leftSum+1,preEnd,inStart+leftSum+1,inEnd);
            return root;
        }
}
````

### 迭代写法：

````java
class Solution {
    /*
    *思路参考：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-		* inorder-traversal/solution/cong-qian-xu-yu-zhong-xu-bian-li-xu-lie-gou-zao-9/
    *
    */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder == null || preorder.length <= 0
            || inorder == null || inorder.length <=0){
            return null;
        }
        int inorderIndex = 0;
        TreeNode root =new TreeNode(preorder[0]);
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        for(int i = 1;i < preorder.length;i++){
            TreeNode node = stack.peek();
            int value =preorder[i];
            if(node.val != inorder[inorderIndex]){
                node.left = new TreeNode(value);
                stack.push(node.left);
            }else{
                while(!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]){
                    node = stack.pop();
                    inorderIndex ++;
                }
                node.right = new TreeNode(value);
                stack.push(node.right);
            }
        }
        return root;
    }
}
````

