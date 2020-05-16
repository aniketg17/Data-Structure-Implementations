public class BST {
    private Node root;

    private class Node {
        int val;
        Node left;
        Node right;

        public Node(int val) {
            this.val = val;
            left = null;
            right = null;
        }
    }

    public BST(int val) {
        this.root = new Node(val);
    }

    public Node getRoot() {
        return root;
    }

    public Node insertNode(Node root, int key) {
        if (root == null) {
            root = new Node(key);
            return root;
        }

        if (root.val >= key) {
            root.left = insertNode(root.left, key);
        } else {
            root.right = insertNode(root.right, key);
        }
        return null;
    }


    public boolean existVal(Node root, int key) {
        if (root == null) {
            return false;
        }

        if (key == root.val) {
            return true;
        }

        if (key < root.val) {
            return existVal(root.left, key);
        } else {
            return existVal(root.right, key);
        }
    }


    public void deleteNode() {

    }


    public static void main(String[] args) {
        BST tree = new BST(5);
        tree.insertNode(tree.getRoot(), 2);
        tree.insertNode(tree.getRoot(), 7);

        System.out.println(tree.root.val);
        System.out.println(tree.root.left.val);
        System.out.println(tree.root.right.val);
        System.out.println(tree.existVal(tree.root, 10));
    }

}
