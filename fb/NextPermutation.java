package fb;

public class NextPermutation {

	public static Integer[] nextPermutation(final Integer... input) {

		int k = input.length - 1;
		int j = k;
		while ((j > 0) && (input[j] <= input[j - 1])) {
			j -= 1;
		}
		if (j > 0) {
			j -= 1;
		}
		if (input[j + 1] <= input[j]) {
			reverse(input, 0, input.length - 1);
			return null;
		}

		while (input[k] <= input[j]) {
			k -= 1;
		}
		swap(input, k, j);
		reverse(input, j + 1, input.length - 1);
		return input;
	}

	private static void reverse(final Integer[] input, int i, int j) {
		while (i < j) {
			swap(input, i, j);
			++i;
			--j;
		}
	}

	private static void swap(final Integer[] input, final int i, final int j) {
		final int t = input[i];
		input[i] = input[j];
		input[j] = t;
	}

	public static void main(final String[] args) {
		final Integer a[] = new Integer[] { 1, 1, 1, 1, 3, 2 };
		while ((nextPermutation(a)) != null) {
			for (int i = 0; i < a.length; ++i) {
				System.out.print(a[i]);
			}
			System.out.println();
		}
	}
}
