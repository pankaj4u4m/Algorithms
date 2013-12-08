/*
 * There are n bombs in a big circle,and each bomb has a value and a 'effect range'.If you detonated a bomb,you will get
 * this bomb's value,but a bomb can have effect on the neighbors which the distance(difference between index) between
 * them is smaller than the 'effect range'.It's say that the neighbor bomb will be destoryed and you could not get their
 * values.
 * You will given each bomb's value and the 'effect range',and you need calculate the max value you can get.
 * eg. n=3 index 0's bomb' v is 2,range is 0(will not effect others).and v[1]=1,r[1]=1,v[2]=3,r[2]=1;
 * this case's max value is 5.(detonate the 0's and than the 2's).
 */
public class G5231103736545280 {

    // do memoization
    static int maxValue(int[] v, int[] r, int start, int end) {
        if (start > end) {
            return 0;
        }
        if (start == end) {
            return v[start];
        }

        int mx = 0;
        for (int i = start; i <= end; ++i) {
            int x = v[i] + maxValue(v, r, start, i - r[i] - 1) + maxValue(v, r, i + r[i] + 1, end);
            mx = Math.max(mx, x);
        }
        return mx;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        int v[] = { 2, 1, 3 };
        int r[] = { 0, 1, 1 };
        System.out.println(maxValue(v, r, 0, 2));
    }

}
