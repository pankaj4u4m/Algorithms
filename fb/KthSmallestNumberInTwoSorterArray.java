package fb;

public class KthSmallestNumberInTwoSorterArray {

    public static Integer kthSmallest(int[] A, int[] B, int i, int j, int n, int m, int k) {
        if (n - i > m - j) {
            return kthSmallest(B, A, j, i, m, n, k);
        } else if (n == i) {
            return B[k-1];
        } else if (k == 1) {
            return Math.min(A[i], B[j]);
        } else {
            int x = Math.min(n - i, k / 2);
            int y = k - x;
            ;
            if (A[i + x - 1] <= B[j + y - 1]) {
                return kthSmallest(A, B, i + x, j, n, m, k - x);
            } else {
                return kthSmallest(A, B, i, j + y, n, m, k - y);
            }
        }
    }


	public static void main(String[] args) {
		int a[] = new int[] { 1, 2, 3, 4, 5, 6, 7 };
		int b[] = new int[] { };
		System.out
				.println(kthSmallest(a, b, 0, 0, a.length, b.length, 6 ));
	}
}
