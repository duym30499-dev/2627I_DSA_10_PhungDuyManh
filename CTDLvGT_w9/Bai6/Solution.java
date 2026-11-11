
import java.util.Scanner;

public class Solution {
    static class Node {
        int val;
        int ht;
        Node left;
        Node right;

        Node(int val) {
            this.val = val;
            this.ht = 0;
        }
    }

    static int height(Node node) {
        if (node == null)
            return -1;
        return node.ht;
    }

    static int balance(Node node) {
        return height(node.left) - height(node.right);
    }

    static Node rotateRight(Node y) {
        Node x = y.left;
        Node t = x.right;

        x.right = y;
        y.left = t;

        y.ht = 1 + Math.max(height(y.left), height(y.right));
        x.ht = 1 + Math.max(height(x.left), height(x.right));

        return x;
    }

    static Node rotateLeft(Node x) {
        Node y = x.right;
        Node t = y.left;

        y.left = x;
        x.right = t;

        x.ht = 1 + Math.max(height(x.left), height(x.right));
        y.ht = 1 + Math.max(height(y.left), height(y.right));

        return y;
    }

    static Node insert(Node root, int val) {
        if (root == null)
            return new Node(val);

        if (val < root.val)
            root.left = insert(root.left, val);
        else if (val > root.val)
            root.right = insert(root.right, val);
        else
            return root;

        root.ht = 1 + Math.max(height(root.left), height(root.right));

        int b = balance(root);

        if (b > 1 && val < root.left.val)
            return rotateRight(root);

        if (b < -1 && val > root.right.val)
            return rotateLeft(root);

        if (b > 1 && val > root.left.val) {
            root.left = rotateLeft(root.left);
            return rotateRight(root);
        }

        if (b < -1 && val < root.right.val) {
            root.right = rotateRight(root.right);
            return rotateLeft(root);
        }

        return root;
    }

    static void inorder(Node root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.val + " ");
            inorder(root.right);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        Node root = null;

        for (int i = 0; i < n; i++)
            root = insert(root, sc.nextInt());

        int val = sc.nextInt();
        root = insert(root, val);

        inorder(root);
        System.out.println();

        sc.close();
    }
}
