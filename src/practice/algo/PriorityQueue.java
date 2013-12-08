package practice.algo;

import java.util.ArrayList;
import java.util.List;

public class PriorityQueue<T extends Comparable<T>> {

    private List<T> heap;
    private int size = 0;

    private void swap(int i, int j) {
        T d = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, d);
    }

    public PriorityQueue() {
        heap = new ArrayList<>();
        size = 0;
    }

    public T extractMax() {
        if (size == 0) {
            return null;
        }
        T m = heap.get(0);
        swap(0, size - 1);
        size--;
        maxHeapify(0);
        return m;
    }

    private void maxHeapify(int p) {
        int left = p * 2;
        int right = p * 2 + 1;
        int maxP = p;
        if (left < size && heap.get(maxP).compareTo(heap.get(left)) < 0) {
            maxP = left;
        }
        if (right < size && heap.get(maxP).compareTo(heap.get(right)) < 0) {
            maxP = right;
        }
        if (maxP != p) {
            maxHeapify(maxP);
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
