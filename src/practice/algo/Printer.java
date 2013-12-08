package practice.algo;

public class Printer {
    public static void print(long[] reduction) {
        System.out.print("[");
        for (int i = 0; i < reduction.length; ++i) {
            System.out.print(reduction[i] + ", ");
        }
        System.out.println("]");
    }

    public static void print(int[] reduction) {
        System.out.print("[");
        for (int i = 0; i < reduction.length; ++i) {
            System.out.print(reduction[i] + ", ");
        }
        System.out.println("]");
    }
}
