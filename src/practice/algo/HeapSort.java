package practice.algo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HeapSort<T extends Comparable<T>> {

    private void swap(List<T> data, int i, int j) {
        T d = data.get(i);
        data.set(i, data.get(j));
        data.set(j, d);
    }

    public void sort(List<T> data) {
        int n = data.size();
        for (int i = n / 2; i >= 0; --i) {
            maxHeapify(data, i, n);
        }
        int heapSize = n;
        for (int i = 0; i < n; ++i) {
            swap(data, heapSize - 1, 0);

            --heapSize;
            maxHeapify(data, 0, heapSize);
        }
    }

    private void maxHeapify(List<T> data, int pos, int size) {
        int left = 2 * pos;
        int right = 2 * pos + 1;

        int maxPos = pos;

        if (left < size && data.get(maxPos).compareTo(data.get(left)) < 0) {
            maxPos = left;
        }
        if (right < size && data.get(maxPos).compareTo(data.get(right)) < 0) {
            maxPos = right;
        }

        if (maxPos != pos) {
            swap(data, pos, maxPos);
            maxHeapify(data, maxPos, size);
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        List<Integer> array = getRandomArray(50);
        List<Integer> array2 = new ArrayList<>(array);
        new HeapSort<Integer>().sort(array);
        Collections.sort(array2);
        assert (!array.equals(array2)) : "not equals";
        System.out.println(array);
        System.out.println(array2);
    }

    private static List<Integer> getRandomArray(int size) {
        List<Integer> array = new ArrayList<>();
        for (int i = 0; i < size; ++i) {
            array.add((int) (Math.random() * 10000));
        }
        return array;
    }

}
