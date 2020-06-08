public class HashMap<Key, Value> implements HashMapInterface{
    private int size;
    private HashNode<Key, Value>[] array;
    private int numberOfKeys;
    private int numberOfSlots;
    private final int INITIAL_ARRAY_SIZE = 13;
    private int currentArraySize;
    private final double MAX_LOAD = 0.75;


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
        return size == 0;
    }

    public Value put(Key k, Value v) {
        if (getLoadFactor() > MAX_LOAD) {
            increaseArraySize();
        }
        int hashcode = getHashCode(k);
        assert (hashcode != -1);
        HashNode<Key, Value> insert = new HashNode<>(k, v);
        if (array[hashcode] == null) {
            array[hashcode] = insert;
        } else {
            insert.next = array[hashcode];
            array[hashcode] = insert;
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
        int hash = getHashCode(k);
        Value value = null;
        HashNode<Key, Value> prev = null;
        for (HashNode<Key, Value> node = array[hash]; node != null; node = node.next) {
            if (node.key.equals(k)) {
                if (prev == null) {
                    value = node.value;
                    array[hash] = array[hash].next;
                    return value;
                } else {
                    value = prev.next.value;
                    prev.next = node.next;
                }
            } else {
                prev = node;
            }
        }
        --numberOfKeys;

        if (getLoadFactor() <= 0.25) {
            decreaseArraySize();
        }

        return value;
    }

    private void decreaseArraySize() {
        HashNode<Key, Value>[] newArray = new HashNode[(int) (0.5 * currentArraySize)];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
        numberOfSlots = array.length;
    }

    public Value get(Key k) {
        int hash = getHashCode(k);
        for (HashNode<Key, Value> node = array[hash]; node != null; node = node.next) {
            if (node.key.equals(k)) {
                return node.value;
            }
        }
        return null;
    }

    public Value replace(Key k, Value v) {
        return null;
    }

    public boolean containsKey(Key k) {
        int hash = getHashCode(k);
        for (HashNode<Key, Value> node = array[hash]; node != null; node = node.next) {
            if (node.key.equals(k)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsValue(Value v) {
        for (HashNode<Key, Value> node : array) {
            if (node != null) {
                for (HashNode<Key, Value> trav = node; trav != null; trav = trav.next) {
                    if (trav.value.equals(v)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    public static void main(String[] args) {
        HashMap<Integer, Integer> hash = new HashMap<>();
        System.out.println(hash.isEmpty());
        hash.put(2, 4);
        hash.put(4,1);
        //hash.put(4,1);
        hash.put(15,1);


    }
}
