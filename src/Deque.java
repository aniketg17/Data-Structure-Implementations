import java.util.EmptyStackException;

public class Deque<T> implements DequeInterface{

    private int size;
    private Node firstSentinel;
    private Node lastSentinel;

    private class Node {
        T data;
        Node next;
        Node prev;

        private Node(T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    public Deque() {
        this.size = 0;
        this.firstSentinel = new Node(null);
        this.lastSentinel = new Node(null);
    }

    @Override
    public int getSize() {
        return size;
    }

    public void insertFirst(T data) {
        Node insertNode = new Node(data);
        if (firstSentinel.next == null && lastSentinel.prev == null) {
            firstSentinel.next = insertNode;
            insertNode.prev = firstSentinel;
            lastSentinel.prev = insertNode;
            insertNode.next = lastSentinel;
        } else {
            insertNode.next = firstSentinel.next;
            assert firstSentinel.next != null;
            firstSentinel.next.prev = insertNode;
            firstSentinel.next = insertNode;
            insertNode.prev = firstSentinel;
        }
        ++size;
    }

    public void insertLast(T data) {
        Node insertNode = new Node(data);
        if (firstSentinel.next == null && lastSentinel.prev == null) {
            firstSentinel.next = insertNode;
            insertNode.prev = firstSentinel;
            lastSentinel.prev = insertNode;
            insertNode.next = lastSentinel;
        } else {
            insertNode.next = lastSentinel;
            assert lastSentinel.prev != null;
            insertNode.prev = lastSentinel.prev;
            lastSentinel.prev.next = insertNode;
            lastSentinel.prev = insertNode;
        }
        ++size;
    }

    @Override
    public T removeFirst() {
        if (firstSentinel.next != null) {
            T returnData = firstSentinel.next.data;
            firstSentinel.next = firstSentinel.next.next;
            --size;
            return returnData;
        } else {
            throw new EmptyStackException();
        }
    }

    @Override
    public T removeLast() {
        if (lastSentinel.prev != null) {
            T returnData = lastSentinel.prev.data;
            lastSentinel.prev = lastSentinel.prev.prev;
            --size;
            return returnData;
        } else {
            throw new EmptyStackException();
        }
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public T first() {
        if (firstSentinel.next != null && firstSentinel.next.data != null) {
            return firstSentinel.next.data;
        } else {
            return null;
        }
    }

    @Override
    public T last() {
        if (lastSentinel.prev != null && lastSentinel.prev.data != null) {
            return lastSentinel.prev.data;
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        Deque<Integer> trialDeque = new Deque<>();
        trialDeque.insertFirst(1);
        trialDeque.insertLast(2);
        trialDeque.insertFirst(0);
        System.out.println(trialDeque.firstSentinel.next.data);
        System.out.println(trialDeque.firstSentinel.next.next.data);
        System.out.println(trialDeque.firstSentinel.next.next.next.data);
        System.out.println(trialDeque.lastSentinel.prev.data);
        System.out.println(trialDeque.lastSentinel.prev.prev.data);
        System.out.println(trialDeque.lastSentinel.prev.prev.prev.data);

        trialDeque.removeFirst();
        trialDeque.removeLast();
        System.out.println(trialDeque.firstSentinel.next.data);
        System.out.println(trialDeque.lastSentinel.prev.data);
        System.out.println(trialDeque.size);
        System.out.println(trialDeque.isEmpty());
        trialDeque.removeLast();
        System.out.println(trialDeque.isEmpty());
    }
}
