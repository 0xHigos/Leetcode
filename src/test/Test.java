package test;

class Node {
    int data;
    Node left;
    Node right;

    Node(int data) {
        this.data = data;
    }
}

public class Test {
    private int maxLen = 0;

    //求解二叉树的深度。
    public int DepthOfBinaryTree(Node root) {
        if (root == null)
            return -1;
        else
            return DepthOfBinaryTree(root.left) > DepthOfBinaryTree(root.right)
                    ? DepthOfBinaryTree(root.left) + 1 : DepthOfBinaryTree(root.right) + 1;
    }
    // 求解最远距离&二叉树的高度。
    public int findMaxLen(Node root) {
        if (root == null || (root.left ==null && root.right ==null)) {
            return 0;
        }
        int leftMaxLen = findMaxLen(root.left)+1;
        int rightMaxLen = findMaxLen(root.right)+1;
        int maxTemp=leftMaxLen+rightMaxLen;
        if(maxTemp >maxLen)
            maxLen =maxTemp;
        return leftMaxLen>rightMaxLen?leftMaxLen:rightMaxLen;
    }
    // 测试
    public static void main(String[] args) {
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node9 = new Node(9);
        Node node10 = new Node(10);
        Node node11 = new Node(11);
        Node node12 = new Node(12);
        Node node13 = new Node(13);

        node2.left = node3;
        node2.right = node4;
        node3.left = node5;
        node3.right = node6;
        node5.left = node7;
        node7.left = node10;
        node10.left = node11;
        node6.right = node9;
        node9.right = node12;
        node12.left = node13;

        Test test = new Test();
        int out =test.findMaxLen(node2);
        System.out.println(out);
        System.out.println(test.maxLen);
    }

}
