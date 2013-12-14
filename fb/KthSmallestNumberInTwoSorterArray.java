package fb;

public class KthSmallestNumberInTwoSorterArray {

    public static Integer kthSmallest(int a[], int b[], int k) {
        int n = a.length;
        int m = b.length;
        if (m + n < k)
            return null;
        if (n + m == k)
            return Math.max(a[n - 1], b[m - 1]);
        if (n > m) {
            int t[] = a;
            a = b;
            b = t;
            int c = n;
            n = m;
            m = c;
        }

        int i = Math.min(n, k / 2);
        int j = k - i;
        int steps = (i + 1) / 2;
        int times = 2;

        while (times > 0) {
            System.out.println(steps + " = " + i + " : " + j);
            if (a[i - 1] > b[j - 1]) {
                i -= steps;
                j += steps;
            } else {
                i += steps;
                j -= steps;
            }
            if (steps == 1) {
                times--;
            }

            steps = (steps + 1) / 2;
        }
        System.out.println(steps + " = " + i + " : " + j);

        int x = medianOf3(a, b, i - 1, j - 1);
        System.out.println(x);
        return x;
    }

    private static Integer medianOf3(int[] a, int[] b, int i, int j) {
        List<Integer> list = new ArrayList<>();
        if (i - 1 >= 0) {
            list.add(a[i - 1]);
        }
        list.add(a[i]);
        if (i + 1 < a.length) {
            list.add(a[i + 1]);
        }
        if (j - 1 >= 0) {
            list.add(b[j - 1]);
        }
        list.add(b[j]);
        if (j + 1 < b.length) {
            list.add(b[j + 1]);
        }
        Collections.sort(list);
        return list.get(list.size() / 2);
    }

	public static void main(String[] args) {
		int a[] = new int[] { 1, 2, 3, 4, 5, 6, 7 };
		int b[] = new int[] { };
		System.out
				.println(kthSmallest(a, b, 6 ));
	}
}
