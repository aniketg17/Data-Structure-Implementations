public class Trie<Value> {

    private static final int MAX_INDICES = 256; // change depending on need. currently suited for ASCII
    private TrieNode root;
    private int size;

    private static class TrieNode {
        private Object val;
        private TrieNode[] next = new TrieNode[MAX_INDICES];
    }

    public Trie() {
        this.root = null;
    }

    public Value insert(String key, Value value) {
        root = insert(root, key, value, 0);
        return value;
    }

    public TrieNode insert(TrieNode root, String key, Value value, int charCount) {
        if (root == null) {
            root = new TrieNode();
        }
        if (charCount == key.length()) {
            root.val = value;
            return root;
        }
        char insert = key.charAt(charCount);
        root.next[insert] = insert(root.next[insert], key, value, charCount + 1);
        return root;
    }

    public static void main(String[] args) {
        Trie<Integer> bsdk = new Trie<>();
        bsdk.insert("Aniket", 1);
        bsdk.insert("Ani", 3);
    }
}
