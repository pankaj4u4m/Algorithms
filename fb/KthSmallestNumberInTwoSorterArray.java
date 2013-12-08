package fb;

public class KthSmallestNumberInTwoSorterArray {

	public static Integer kthSmallest(int a[], int b[], int n, int m, int k) {
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
		if( n == 0) return b[k-1];
		int i = Math.min(n, k / 2);
		int j = k - i;
		int steps = i / 4;
		while (steps > 0) {
			System.out.println(steps + " = " + i + " : " + j);
			if (a[i -1] > b[j-1]) {
					i -= steps;
					j += steps;
			} else {
					i += steps;
					j -= steps;
			}
			steps >>= 1;
		}
		System.out.println(steps + " = " + i + " : " + j);
		return Math.max(a[i-1], b[j-1]);
	}

	public static void main(String[] args) {
		int a[] = new int[] { 1, 2, 3, 4, 5, 6, 7 };
		int b[] = new int[] { };
		System.out
				.println(kthSmallest(a, b, a.length, b.length , 6 ));
	}
}
