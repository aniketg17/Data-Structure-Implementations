public class Trie<Value> {

    private final int MAX_INDICES = 256; // change depending on need. currently suited for ASCII
    private TrieNode root;
    private int size;

    private class TrieNode {
        Value v;
        Trie[] next = new Trie[MAX_INDICES];
    }

    public Trie() {
        this.root = null;
    }

}
