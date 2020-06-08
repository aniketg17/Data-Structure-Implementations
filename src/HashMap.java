import java.util.Arrays;

public class HashMap<Key, Value> implements HashMapInterface{
    private HashNode<Key, Value>[] array;
    private int numberOfKeys;
    private int numberOfSlots;
    private final int INITIAL_ARRAY_SIZE = 5;
    private int currentArraySize;
    private final double MAX_LOAD = 0.75;
    private final double MIN_LOAD = 0.25;

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
        this.numberOfKeys = 0;
        currentArraySize = INITIAL_ARRAY_SIZE;
        array = new HashNode[INITIAL_ARRAY_SIZE]; // cast because generic arrays not allowed
    }

    @Override
    public void clear() {
        numberOfKeys = 0;
        Arrays.fill(array, null);
    }

    @Override
    public boolean isEmpty() {
        return numberOfKeys == 0;
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
            ++numberOfKeys;
        } else {
            boolean duplicate = false;
            for (HashNode<Key, Value> node = array[hashcode]; node != null; node = node.next) {
                if (node.key.equals(k)) {
                    node.value = v;
                    duplicate = true;
                }
            }
            if (!duplicate) {
                insert.next = array[hashcode];
                array[hashcode] = insert;
                ++numberOfKeys;
            }
        }
        return v;
    }

    private void increaseArraySize() {
        numberOfKeys = 0;
        HashNode<Key, Value>[] newArray = new HashNode[2 * currentArraySize];
        HashNode<Key, Value>[] oldArray = array;
        array = newArray;
        numberOfSlots = array.length;
        currentArraySize = array.length;
        for (HashNode<Key, Value> node : oldArray) {
            if (node != null) {
                for (HashNode<Key, Value> trav = node; trav != null; trav = trav.next) {
                    put(trav.key, trav.value);
                }
            }
        }
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
                --numberOfKeys;
                if (prev == null) {
                    value = node.value;
                    array[hash] = array[hash].next;
                } else {
                    value = prev.next.value;
                    prev.next = node.next;
                }
            } else {
                prev = node;
            }
        }
        if (getLoadFactor() <= MIN_LOAD) {
            decreaseArraySize();
        }

        return value;
    }

    private void decreaseArraySize() {
        numberOfKeys = 0;
        HashNode<Key, Value>[] newArray = new HashNode[(int) (0.5 * currentArraySize)];
        HashNode<Key, Value>[] oldArray = array;
        array = newArray;
        numberOfSlots = array.length;
        currentArraySize = array.length;

        for (HashNode<Key, Value> node : oldArray) {
            if (node != null) {
                for (HashNode<Key, Value> trav = node; trav != null; trav = trav.next) {
                    put(trav.key, trav.value);
                }
            }
        }
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
        int hash = getHashCode(k);
        for (HashNode<Key, Value> node = array[hash]; node != null; node = node.next) {
            if (node.key.equals(k)) {
                node.value = v;
                return v;
            }
        }
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
        return numberOfKeys;
    }

    public static void main(String[] args) {
        HashMap<Integer, Integer> hash = new HashMap<>();
        System.out.println(hash.isEmpty());
        hash.put(2, 4);

        hash.put(4,9);

        hash.put(15,1);
        hash.put(30, 2);
        hash.put(80, 8);

        System.out.println(hash.get(80));
        hash.remove(15);
        hash.remove(2);
        hash.remove(4);
        hash.remove(30);
        hash.remove(80);
        System.out.println(hash.isEmpty());
    }
}
