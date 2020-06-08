public class HashMap<Key, Value> implements HashMapInterface{
    private int size;

    private class HashNode<Key, Value> {
        Key key;
        Value value;
        HashNode<Key, Value> next;

        public HashNode(Key key, Value value) {
            this.key = key;
            this.value = value;
            next = null;
        }
    }

    public HashMap() {
        this.size = 0;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Object put(Object k, Object v) {
        return null;
    }

    @Override
    public Object remove(Object k) {
        return null;
    }

    @Override
    public Object get(Object k) {
        return null;
    }

    @Override
    public Object replace(Object k, Object v) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }
}
