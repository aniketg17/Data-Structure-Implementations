public class HashMap<Key, Value> implements HashMapInterface{
    private int size;
    private HashNode<Key, Value>[] array;
    private int numberOfKeys;
    private int numberOfSlots;
    private final int INITIAL_ARRAY_SIZE = 13;
    private int currentArraySize;


    private static class HashNode<Key, Value> {
        Key key;
        Value value;
        HashNode<Key, Value> next;

        public HashNode(Key key, Value value) {
            this.key = key;
            this.value = value;
            next = null;
        }
    }

    private int getNumberOfKeys() {
        return numberOfKeys;
    }

    private int getNumberOfSlots() {
        return numberOfSlots;
    }

    private double getLoadFactor() {
        return (double) getNumberOfKeys() / getNumberOfSlots(); // number of keys per slot
    }

    public HashMap() {
        this.numberOfSlots = INITIAL_ARRAY_SIZE;
        this.size = 0;
        currentArraySize = INITIAL_ARRAY_SIZE;
        array = new HashNode[INITIAL_ARRAY_SIZE]; // cast because generic arrays not allowed
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    public Value put(Key k, Value v) {
        if (getLoadFactor() > 0.75) {
            increaseArraySize();
        }
        int hashcode = getHashCode(k);
        assert (hashcode != -1);
        HashNode<Key, Value> insert = new HashNode<>(k, v);
        if (array[hashcode] == null) {
            array[hashcode] = insert;
        } else {
            HashNode node = array[hashcode];
            while (node.next != null) {
                node = node.next;
            }
            node.next = insert;
        }
        ++size;
        ++numberOfKeys;
        return v;
    }

    private void increaseArraySize() {
        HashNode<Key, Value>[] newArray = new HashNode[2 * currentArraySize];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
        numberOfSlots = array.length;
    }

    public int getHashCode(Key k) {
        if (k != null) {
            return k.hashCode() % numberOfSlots;
        } else {
            return -1;
        }
    }

    public Value remove(Key k) {
        return null;
    }

    public Value get(Object k) {
        return null;
    }

    public Value replace(Object k, Object v) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }
}
