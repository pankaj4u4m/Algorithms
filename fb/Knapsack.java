package fb;

public class Knapsack {

	public static int knapsack(final int c, final int w[], final int n,
			final int m[][]) {
		if (n < 0) {
			return c;
		}
		if (m[n][w[n]] == 0) {
			int mx = 1000;
			if (c >= w[n]) {
				mx = knapsack(c - w[n], w, n - 1, m);
			}
			mx = Math.min(mx, knapsack(c, w, n - 1, m));
			m[n][w[n]] = mx;
		}
		return m[n][w[n]];
	}

	public static void main(final String[] args) {
		final int c = 10;
		final int w[] = new int[] { 2, 3, 4, 5 };
		final int m[][] = new int[4][c];
		System.out.println(c - knapsack(c, w, w.length - 1, m));
	}
}
