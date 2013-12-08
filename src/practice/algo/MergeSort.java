package practice.algo;

import java.util.Arrays;

public class MergeSort {

    public void sort(int[] array, int start, int end) {
        if (end - start < 1) {
            return;
        }
        int mid = (start + end) / 2;
        sort(array, start, mid);
        sort(array, mid + 1, end);
        int result[] = new int[end - start + 1];
        int left = start;
        int right = mid + 1;
        for (int i = 0; i < end - start + 1; ++i) {
            if (right > end) {
                result[i] = array[left];
                left++;
            } else if (left > mid) {
                result[i] = array[right];
                right++;
            } else if (array[left] < array[right]) {
                result[i] = array[left];
                left++;
            } else {
                result[i] = array[right];
                right++;
            }
        }
        for (int i = 0; i < end - start + 1; ++i) {
            array[start + i] = result[i];
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        int[] array = getRandomArray(50);
        int[] copy = Arrays.copyOf(array, 50);

        new MergeSort().sort(array, 0, 49);

        // Printer.print(array);
        Arrays.sort(copy);
        // Printer.print(copy);

    }

    private static int[] getRandomArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; ++i) {
            array[i] = (int) (Math.random() * 10000);
        }
        return array;
    }
}
