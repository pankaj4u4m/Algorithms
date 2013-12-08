package fb;

public class SubsetOfSizeK {

	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		final int a[] = { 1, 2, 3, 4 };
		final int b[] = new int[3];
		printSubset(a, b, 0, 2);
	}

	private static void printSubset(final int[] a, final int[] b, final int s,
			final int k) {
		if (k < 0) {
			return;
		}
		if (k == 0) {
			for (int i = s; i < a.length; ++i) {
				for (int j = b.length - 1; j > 0; --j) {
					System.out.print(b[j]);
				}
				System.out.println(a[i]);
			}
		}
		for (int i = s; i < a.length - k; ++i) {
			b[k] = a[i];
			printSubset(a, b, i + 1, k - 1);
		}
	}

}
