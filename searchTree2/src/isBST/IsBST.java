package isBST;

class Node {
    int data;
    Node left;
    Node right;

    public Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}

public class IsBST {
    public static Node prevNode = null;

    public static boolean isBST1(Node root) {
        if (root != null) {
            if (!isBST1(root.left))
                return false;
            if (prevNode != null && prevNode.data >= root.data) {
                return false;
            }
            prevNode = root;
            return isBST1(root.right);
        }
        return true;
    }

    public static void main(String args[]) {
        Node root = new Node(20);
        root.left = new Node(10);
        root.right = new Node(30);
        root.left.left = new Node(5);
        root.left.right = new Node(15);
        root.right.left = new Node(25);
        root.right.right = new Node(35);
        System.out.println(isBST1(root));
    }
}