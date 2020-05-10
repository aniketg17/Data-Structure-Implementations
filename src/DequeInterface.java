public interface DequeInterface<T> {
    int getSize();
    T removeFirst();
    T removeLast();
    boolean isEmpty();
    T first();
    T last();
}
