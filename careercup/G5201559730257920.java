/**
 * Give you an array which has n integers,it has both positive and negative integers.Now you need sort this array in a
 * special way.After that,the negative integers should in the front,and the positive integers should in the back.Also
 * the relative position should not be changed.
 * eg. -1 1 3 -2 2 ans: -1 -2 1 3 2.
 * o(n)time complexity and o(1) space complexity is perfect.
 * 
 * @author kpankaj
 */
public class G5201559730257920 {

    public void sort(int[] array, int n) {
        int negative = 0;
        for (int i = 0; i < n; ++i) {
            if (array[i] < 0) {
                ++negative;
            }
        }
        int positive = n - negative;
        int total = n;
        int prev = 0;
        int next = 0;
        for (int i = n - 1; i >= 0; ++i) {
            int x = array[i];
            do {
                if (x < 0) {
                    prev = i;
                    next = negative - 1;
                    negative--;
                } else {
                    prev = i;
                    next = total - 1;
                    total--;
                }
                int t = array[next];
                array[next] = x;
                x = t;
            } while (prev != next);

        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
