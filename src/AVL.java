public class AVL {
    private class Node {
        Node left;
        Node right;
        int val;
        int height;

        public Node(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
            this.height = 1;
        }
    }

    Node root;

    public Node getRoot() {
        return root;
    }

    public Node rotateRight(Node root) {
        Node newParent = root.left;
        Node childSwitch = newParent.right;

        newParent.right = root;
        newParent.right.left = childSwitch;

        newParent.right.height = 1 + Math.max(getHeight(newParent.right.right), getHeight(newParent.right.left));
        newParent.height = 1 + Math.max(getHeight(newParent.left), getHeight(newParent.right));

        return newParent;
    }

    public Node rotateLeft(Node root) {
        Node newParent = root.right;
        Node childSwitch = newParent.left;

        newParent.left = root;
        newParent.left.right = childSwitch;

        newParent.left.height = 1 + Math.max(getHeight(newParent.left.right), getHeight(newParent.left.left));
        newParent.height = 1 + Math.max(getHeight(newParent.left), getHeight(newParent.right));

        return newParent;
    }

    public int getHeight(Node root) {
        if (root == null) {
            return 0;
        }
        return root.height;
    }

    public Node insert(Node root, int val) {
        if (this.root == null) {
            this.root = new Node(val);
            return this.root;
        }
        if (root == null) {
            return new Node(val);
        }
        if (val < root.val) {
            root.left = insert(root.left, val);
        } else if (val > root.val) {
            root.right = insert(root.right, val);
        } else {
            return root;
        }
        root.height = 1 + Math.max(getHeight(root.left), getHeight(root.right));
        int balance = getHeight(root.left) - getHeight(root.right);

        if (balance > 1 && val < root.left.val) {
            // rotate right
            root = rotateRight(root);
        } else if (balance < -1 && val > root.right.val) {
            // rotate left
            root = rotateLeft(root);
        } else if (balance > 1 && val > root.left.val) {
            //rotate left then rotate right
            root.left = rotateLeft(root.left);
            root = rotateRight(root);
        } else if (balance < -1 && val < root.right.val) {
            // rotate right then rotate left
            root.right = rotateRight(root.right);
            root = rotateLeft(root);
        }
        return root;
    }

    public void preOrder(Node root) {
        if (root == null) {
            return;
        }

        System.out.println(root.val + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    public static void main(String[] args) {
        AVL tree = new AVL();
        tree.root = tree.insert(tree.getRoot(), 4);
        tree.root = tree.insert(tree.getRoot(), 1);
        tree.root = tree.insert(tree.getRoot(), 7);
        tree.root = tree.insert(tree.getRoot(), 0);
        tree.root = tree.insert(tree.getRoot(), 3);
        tree.root = tree.insert(tree.getRoot(), 2);

//        tree.root = tree.insert(tree.root, 10);
//        tree.root = tree.insert(tree.root, 20);
//        tree.root = tree.insert(tree.root, 30);
//        tree.root = tree.insert(tree.root, 40);
//        tree.root = tree.insert(tree.root, 50);
//        tree.root = tree.insert(tree.root, 25);

        tree.preOrder(tree.root);
    }
}