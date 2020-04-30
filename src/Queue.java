public class Queue<T> implements QueueInterface {
    private int size;
    private Node last;
    private Node first;

    private class Node {
        T val;
        Node next;

        private Node(T val) {
            this.val = val;
            this.next = null;
        }
    }

    private Queue() {
        this.size = 0;
        this.first = null;
        this.last = null;
    }

    public void enqueue(T val) {
        if (last == null || first == null) {
            Node create = new Node(val);
            first = create;
            last = create;
        } else {
            last.next = new Node(val);
            last = last.next;
        }
        ++size;
    }

    @Override
    public T dequeue() throws EmptyQueueException {
        if (first == null) {
            throw new EmptyQueueException("Queue is empty");
        }
        T val = first.val;
        first = first.next;
        --size;
        return val;
    }

    @Override
    public T front() throws EmptyQueueException {
        if (first == null) {
            throw new EmptyQueueException("Queue is empty");
        }
        return first.val;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void clear() {
        Node curr = first;
        last = null;
        while (curr != null) {
            Node temp = curr;
            curr = curr.next;
            temp.val = null;
        }
        first = null;
    }

    public static void main(String[] args) throws EmptyQueueException {
        Queue<Integer> my = new Queue<>();
        System.out.println(my.isEmpty());
        my.enqueue(1);
        my.enqueue(2);
        my.enqueue(3);
        System.out.println(my.getSize());
        System.out.println(my.dequeue());
        System.out.println(my.isEmpty());
        System.out.println(my.front());
    }
}
