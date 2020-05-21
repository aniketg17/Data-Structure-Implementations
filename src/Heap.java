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

        int[] sorted = heap.heapSortDescendingRecursive(unsorted);

        for (int x : sorted) {
            System.out.print(x + " ");
        }
        System.out.println();
    }


    public int[] heapSortDescendingRecursive(int[] array) {
        heapifyRecursiveMin(0);
        while (heapSize > 0) {
            int temp = array[0];
            array[0] = array[heapSize - 1];
            array[heapSize - 1] = temp;

            --heapSize;

            heapifyRecursiveMin(0);
        }
        return array;
    }

    public void heapifyRecursiveMin(int idx) {
        if (idx >=  heapSize) {
            return;
        }
        int left = getLeft(idx);
        int right = getRight(idx);

        heapifyRecursiveMin(left);
        heapifyRecursiveMin(right);

        int min = idx;

        if (left < heapSize && array[left] < array[min]) {
            min = left;
        }

        if (right < heapSize && array[right] < array[min]) {
            min = right;
        }

        if (min != idx) {
            int temp = array[min];
            array[min] = array[idx];
            array[idx] = temp;
        }
    }




    public int[] heapSortAscendingRecursive(int[] array) {
        heapifyRecursiveMax(0);
        while (heapSize > 0) {
            int temp = array[0];
            array[0] = array[heapSize - 1];
            array[heapSize - 1] = temp;

            --heapSize;

            heapifyRecursiveMax(0);
        }
        return array;
    }

    public void heapifyRecursiveMax(int idx) {
        if (idx >= heapSize) {
            return;
        }
        int left = getLeft(idx);
        int right = getRight(idx);

        heapifyRecursiveMax(left);
        heapifyRecursiveMax(right);


        int largest = idx;

        if (left < heapSize && array[left] > array[largest]) {
            largest = left;
        }
        if (right < heapSize && array[right] > array[largest]) {
            largest = right;
        }

        if (largest != idx) {
            int temp = array[largest];
            array[largest] = array[idx];
            array[idx] = temp;
        }
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
