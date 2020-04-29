public class Stack<T> implements StackInterface {
    private int size;
    private Node top;

    private class Node {
        T value;
        Node next;

        Node(T value) {
            this.value = value;
            this.next = null;
        }
    }

    private Stack() {
        this.size = 0;
        top = null;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public int getSize() {
        return size;
    }

    private void push(T val) {
        if (this.isEmpty()) {
            top = new Node(val);
        } else {
            Node temp = top;
            top = new Node(val);
            top.next = temp;
        }
        ++size;
    }

    @Override
    public T pop() {
        T value = top.value;
        if (top.next != null) {
            top = top.next;
        } else {
            top = null;
        }
        --size;
        return value;
    }

    @Override
    public T peek() {
        return top.value;
    }

    @Override
    public void clear() {
        while (top != null) {
            Node temp = top;
            top = top.next;
            temp.value = null;
        }
        size = 0;
    }

    public static void main(String[] args) {
        Stack<Integer> trial = new Stack<>();
        trial.push(3);
        trial.push(4);
        trial.push(5);
        trial.push(6);
        System.out.println(trial.getSize());
        System.out.println(trial.peek());
     //   trial.clear();
        System.out.println(trial.isEmpty());
    }
}
