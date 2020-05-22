public class Heap {
    private int[] array;
    private int heapSize;

    public Heap(int[] unsorted) {
        this.array = unsorted;
        this.heapSize = unsorted.length;
    }


    public static void main(String[] args) {
        int[] unsorted = {6,3,5,2,8,1};
        Heap heap = new Heap(unsorted);

        int[] sorted = heap.heapSortDescending();

        for (int x : sorted) {
            System.out.print(x + " ");
        }
        System.out.println();
    }

    public int[] heapSortDescending() {
        int[] heaped = buildMinHeap(array);

        while (heapSize != 0) {

            int temp = heaped[0];
            heaped[0] = heaped[heapSize - 1];
            heaped[heapSize - 1] = temp;

            --heapSize;

            minHeapify(array, 0);
        }

        return heaped;
    }

    public int[] buildMinHeap(int[] array) {
        for (int i = (heapSize / 2) - 1; i >= 0; i--) {
            minHeapify(array, i);
        }
        return array;
    }

    public void minHeapify(int[] array, int idx) {
        int left = getLeft(idx);
        int right = getRight(idx);
        int smallest = idx;

        if (left < heapSize && array[left] < array[smallest]) {
            smallest = left;
        }
        if (right < heapSize && array[right] < array[smallest]) {
            smallest = right;
        }

        if (smallest != idx) {
            int temp = array[smallest];
            array[smallest] = array[idx];
            array[idx] = temp;

            minHeapify(array, smallest);
        }
    }



    public int[] heapSortAscending() {
        int[] heaped = buildMaxHeap(array);

        while (heapSize != 0) {

            int temp = heaped[0];
            heaped[0] = heaped[heapSize - 1];
            heaped[heapSize - 1] = temp;

            --heapSize;

            maxHeapify(heaped, 0);
        }

        return heaped;

    }

    public int[] buildMaxHeap(int[] array) {
        for (int i = (heapSize / 2) - 1; i >= 0; i--) {
            maxHeapify(array, i);
        }
        return array;
    }

    public void maxHeapify(int[] array, int idx) {
        int left = getLeft(idx);
        int right = getRight(idx);
        int largest = idx;

        if (left < heapSize && array[left] > array[largest]) {
            largest = left;
        }
        if (right < heapSize && array[right] > array[largest]) {
            largest = right;
        }

        if (idx != largest) {
            int temp = array[largest];
            array[largest] = array[idx];
            array[idx] = temp;
            maxHeapify(array, largest);
        }
    }

    public int getLeft(int idx) {
        return (idx * 2) + 1;
    }

    public int getRight(int idx) {
        return (idx * 2) + 2;
    }

    public int getParent(int idx) {
        return idx / 2;
    }
}
