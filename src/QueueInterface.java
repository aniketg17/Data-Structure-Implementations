public interface QueueInterface<T> {
    T dequeue() throws EmptyQueueException;
    T front() throws EmptyQueueException;
    boolean isEmpty();
    int getSize();
    void clear();
}
