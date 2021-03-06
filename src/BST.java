public class BST {
    private TreeNode root;

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
            left = null;
            right = null;
        }
    }

    public BST(int val) {
        this.root = new TreeNode(val);
    }

    public TreeNode getRoot() {
        return root;
    }

    public TreeNode insertNodeRecursive(TreeNode root, int key) {
        if (root == null) {
            root = new TreeNode(key);
            return root;
        }

        if (root.val >= key) {
            root.left = insertNodeRecursive(root.left, key);
        } else {
            root.right = insertNodeRecursive(root.right, key);
        }
        return root;
    }


    public TreeNode insertNodeIterative(TreeNode root, int key) {
        if (root == null) {
            root = new TreeNode(key);
            return root;
        }

        TreeNode traverse = root;
        while (true) {
            if (key <= traverse.val) {
                if (traverse.left == null) {
                    traverse.left = new TreeNode(key);
                    break;
                } else {
                    traverse = traverse.left;
                }
            } else {
                if (traverse.right == null) {
                    traverse.right = new TreeNode(key);
                    break;
                } else {
                    traverse = traverse.right;
                }
            }
        }
        return root;
    }

    public boolean existVal(TreeNode root, int key) {
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


    public TreeNode deleteNode(TreeNode root, int val) {
        if (root == null) {
            return null;
        }

        if (val < root.val) {
            root.left = deleteNode(root.left, val);
        } else if (val > root.val) {
            root.right = deleteNode(root.right, val);
        } else {
            if (root.left != null && root.right != null) {
                TreeNode replacement = findPredecessor(root.left);
                root.val = replacement.val;
                root.left = deleteNode(root.left, replacement.val);
            } else if (root.left != null) {
                root = root.left;
            } else if (root.right != null) {
                root = root.right;
            } else {
                root = null;
            }
        }
        return root;
    }

    public TreeNode findPredecessor(TreeNode traverse) {
        while (traverse.right != null) {
            traverse = traverse.right;
        }
        return traverse;
    }

    public TreeNode findSuccessor(TreeNode traverse) {
        while (traverse.left != null) {
            traverse = traverse.left;
        }
        return traverse;
    }

    public TreeNode findParent(TreeNode root, int val) {
        if (root == null || root.val == val) {
            return null;
        }

        if (root.left != null && root.left.val == val ||
                root.right != null && root.right.val == val) {
            return root;
        }

        if (val <= root.val) {
            return findParent(root.left, val);
        } else {
            return findParent(root.right, val);
        }
    }


    public void inorderWalkPrint(TreeNode root) {
        if (root == null) {
            return;
        }
        inorderWalkPrint(root.left);
        System.out.println(root.val);
        inorderWalkPrint(root.right);
    }

    public void preorderWalkPrint(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.println(root.val);
        inorderWalkPrint(root.left);
        inorderWalkPrint(root.right);
    }


    public void postorderWalkPrint(TreeNode root) {
        if (root == null) {
            return;
        }
        inorderWalkPrint(root.left);
        inorderWalkPrint(root.right);
        System.out.println(root.val);
    }

    public static void main(String[] args) {
        BST tree = new BST(30);
        tree.insertNodeIterative(tree.getRoot(), 6);
        tree.insertNodeIterative(tree.getRoot(), 37);
        tree.insertNodeIterative(tree.getRoot(), 1);
        tree.insertNodeIterative(tree.getRoot(), 5);
        tree.insertNodeIterative(tree.getRoot(), 4);
        tree.insertNodeIterative(tree.getRoot(), 3);
        tree.insertNodeIterative(tree.getRoot(), 19);
        tree.insertNodeIterative(tree.getRoot(), 15);
        tree.insertNodeIterative(tree.getRoot(), 14);
        //tree.insertNode(tree.getRoot(), 7);


   //     tree.deleteNode(tree.getRoot(), 6);
        tree.inorderWalkPrint(tree.getRoot());
    }

}
