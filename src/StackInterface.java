public interface StackInterface<T> {
    boolean isEmpty();
    int getSize();
    T pop();
    T peek();
    void clear();
}
