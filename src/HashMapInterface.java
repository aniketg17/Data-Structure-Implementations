public interface HashMapInterface<Key, Value> {
    void clear();
    boolean isEmpty();
    Value put(Key k, Value v);
    Value remove(Key k);
    Value get(Key k);
    Value replace(Key k, Value v);
    int size();
}
