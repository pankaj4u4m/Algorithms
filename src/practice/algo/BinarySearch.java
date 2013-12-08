package practice.algo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BinarySearch<T extends Comparable<T>> {

    public int search(List<T> data, T value) {
        int start = 0;
        int end = data.size() - 1;
        while (end >= start) {
            int mid = start + (end - start) / 2;
            if (data.get(mid).compareTo(value) == 0) {
                return mid;
            } else if (data.get(mid).compareTo(value) > 0) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        List<Integer> array = getRandomArray(50);
        Collections.sort(array);
        int p = (int) (Math.random() * 49);
        int r = new BinarySearch<Integer>().search(array, array.get(p));
        System.out.println(p + " : " + r);
    }

    private static List<Integer> getRandomArray(int size) {
        List<Integer> array = new ArrayList<>();
        for (int i = 0; i < size; ++i) {
            array.add((int) (Math.random() * 10000));
        }
        return array;
    }

}
