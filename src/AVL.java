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


    public Node delete(Node root, int key) {
        if (root == null) {
            return null;
        }

        if (key < root.val) {
            root.left = delete(root.left, key);
        } else if (key > root.val) {
            root.right = delete(root.right, key);
        } else {
            if (root.left != null && root.right != null) {
                Node replacement = predecessor(root.left);
                root.val = replacement.val;
                root.left = delete(root.left, replacement.val);
            } else if (root.left != null) {
                root = root.left;
            } else if (root.right != null) {
                root = root.right;
            } else {
                return null;
            }
        }

        root.height = 1 + Math.max(getHeight(root.left), getHeight(root.right));
        int difference = getHeight(root.left) - getHeight(root.right);

        if (difference > 1 && getHeight(root.left.left) >= getHeight(root.left.right)) {
            root = rotateRight(root);
        } else if (difference < -1 && getHeight(root.right.right) >= getHeight(root.right.left)) {
            root = rotateLeft(root);
        } else if (difference > 1 && getHeight(root.left.left) < getHeight(root.left.right)) {
            root.left = rotateLeft(root.left);
            root = rotateRight(root);
        } else if (difference < -1 && getHeight(root.right.right) < getHeight(root.right.left)) {
            root.right = rotateRight(root.right);
            root = rotateLeft(root);
        }

        return root;
    }

    public Node predecessor(Node root) {
        while (root.right != null) {
            root = root.right;
        }
        return root;
    }


    public void preOrder(Node root) {
        if (root == null) {
            return;
        }

        System.out.print(root.val + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    public static void main(String[] args) {
        AVL tree = new AVL();

        tree.root = tree.insert(tree.getRoot(), 44);
        tree.root = tree.insert(tree.getRoot(), 62);
        tree.root = tree.insert(tree.getRoot(), 17);
        tree.root = tree.insert(tree.getRoot(), 10);
        tree.root = tree.insert(tree.getRoot(), 32);
        tree.root = tree.insert(tree.getRoot(), 50);
        tree.root = tree.insert(tree.getRoot(), 78);
        tree.root = tree.insert(tree.getRoot(), 21);
        tree.root = tree.insert(tree.getRoot(), 48);
        tree.root = tree.insert(tree.getRoot(), 54);
        tree.root = tree.insert(tree.getRoot(), 72);
        tree.root = tree.insert(tree.getRoot(), 88);
        tree.root = tree.insert(tree.getRoot(), 45);
        tree.root = tree.insert(tree.getRoot(), 49);
        tree.root = tree.insert(tree.getRoot(), 52);
        tree.root = tree.insert(tree.getRoot(), 56);
        tree.root = tree.insert(tree.getRoot(), 81);
        tree.root = tree.insert(tree.getRoot(), 92);

        tree.root = tree.delete(tree.getRoot(), 32);

        System.out.println("pre:");
        tree.preOrder(tree.root);
        tree.root = tree.delete(tree.root, 10);
        System.out.println("\npost:");
        tree.preOrder(tree.root);
    }
}