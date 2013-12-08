package practice.algo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InsertionSort<T extends Comparable<T>> {

    public void sort(List<T> a) {
        int n = a.size();
        for (int i = 1; i < n; ++i) {
            T e = a.get(i);
            int j = i - 1;
            while (j >= 0 && e.compareTo(a.get(j)) < 0) {
                a.set(j + 1, a.get(j));
                --j;
            }
            a.set(j + 1, e);
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        List<Integer> array = getRandomArray(50);
        List<Integer> array2 = new ArrayList<>(array);
        new InsertionSort<Integer>().sort(array);
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
