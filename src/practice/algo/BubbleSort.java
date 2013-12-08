package practice.algo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BubbleSort {

  public void sort(List< Integer > array) {
    int n = array.size();
    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < n - 1; ++j) {
        if (array.get(j) > array.get(j + 1)) {
          int t = array.get(j);
          array.set(j, array.get(j + 1));
          array.set(j + 1, t);
        }
      }
    }
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    List< Integer > array = getRandomArray(50);
    List< Integer > array2 = new ArrayList<>(array);
    new BubbleSort().sort(array);
    Collections.sort(array2);
    assert (!array.equals(array2)) : "not equals";
    System.out.println(array);
    System.out.println(array2);
  }

  private static List< Integer > getRandomArray(int size) {
    List< Integer > array = new ArrayList<>();
    for (int i = 0; i < size; ++i) {
      array.add((int) (Math.random() * 10000));
    }
    return array;
  }
}
