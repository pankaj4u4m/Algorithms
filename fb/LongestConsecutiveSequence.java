package fb;

public class LongestConsecutiveSequence {

	public static void longestConsecutiveSequence(final Integer[] a,
			final Integer[] max, final Integer[] min, final int length) {
		// System.out.println(length);
		if (length == 1) {
			max[length - 1] = a[length - 1];
			min[length - 1] = a[length - 1];
			return;
		}
		for (int i = length - 2; i >= 0; --i) {
			longestConsecutiveSequence(a, max, min, i + 1);
			int x = max[i];
			int y = min[i];
			if (x == a[length - 1] - 1) {
				x = a[length - 1];
			} else if (y == a[length - 1] + 1) {
				y = a[length - 1];
			}
			if ((max[length - 1] == null) || (min[length - 1] == null)
					|| (max[length - 1] - min[length - 1] < x - y)) {
				max[length - 1] = x;
				min[length - 1] = y;
			}
		}
	}

	public static void main(final String args[]) {
		final Integer[] a = new Integer[] { 10, 21, 45, 22, 7, 2, 67, 19, 13,
				45, 12, 11, 18, 16, 17, 100, 201, 20, 101 };
		final Integer[] max = new Integer[a.length];
		final Integer[] min = new Integer[a.length];
		longestConsecutiveSequence(a, max, min, a.length);
		for (int i = min[a.length - 1]; i <= max[a.length - 1]; ++i) {
			System.out.print(i + " , ");
		}
	}
}
