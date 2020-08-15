import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

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
            if (root.val == null) {
                ++size;
            }
            root.val = value;
            return root;
        }
        char insert = key.charAt(charCount);
        root.next[insert] = insert(root.next[insert], key, value, charCount + 1);
        return root;
    }

    public Value get(String key) {
        return get(root, key, 0);
    }

    public Value get(TrieNode root, String key, int charCount) {
        if (root == null) return null;
        if (charCount == key.length()) return (Value) root.val;
        char nextChar = key.charAt(charCount);
        return get(root.next[nextChar], key, charCount + 1);
    }

    public static void main(String[] args) {
        Trie<Integer> trial = new Trie<>();
        trial.insert("Aniket", 1);
        trial.insert("Ani", 3);
        trial.insert("Jerome", 9);

        System.out.println(trial.size);
    }

    public Iterator<String> collect() {
        return collectAll();
    }

    public Iterator<String> collectAll() {
        Queue<String> queue = new LinkedList<>();
        addToQueue(root, queue);
        return (Iterator<String>) queue;
    }

    private void addToQueue(TrieNode root, Queue<String> queue) {

    }
}
