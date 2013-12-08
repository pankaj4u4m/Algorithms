package practice.algo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuickSort<T extends Comparable<T>> {

    private void swap(List<T> data, int i, int j) {
        T d = data.get(i);
        data.set(i, data.get(j));
        data.set(j, d);
    }

    public void sort(List<T> data) {
        sort(data, 0, data.size() - 1);
    }

    private void sort(List<T> data, int start, int end) {
        if (end <= start) {
            return;
        }
        int p = partition(data, start, end);
        sort(data, start, p - 1);
        sort(data, p + 1, end);
    }

    private int partition(List<T> data, int start, int end) {
        int r = (int) Math.random() * (end - start) + start;
        T v = data.get(r);
        swap(data, r, end);
        int j = start;
        for (int i = start; i < end; ++i) {
            if (v.compareTo(data.get(i)) > 0) {
                swap(data, i, j);
                j++;
            }
        }
        swap(data, j, end);
        return j;
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
