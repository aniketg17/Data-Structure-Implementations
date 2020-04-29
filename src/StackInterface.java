public interface StackInterface<T> {
    int size = 0;
    boolean isEmpty();
    int getSize();
    T pop();
    T peek();
    void clear();
}
