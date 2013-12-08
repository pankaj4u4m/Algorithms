package fb;

public class NChairsRemoveDthChairEachTime {

	public static int f(final int n, final int k) {
		int x;
		if (n == 1) {
			x = 1;
		} else {
			x = (f(n - 1, k) + k - 1) % n + 1;
		}
		System.out.println(n + " : " + k + " : " + x);
		return x;
	}

	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		System.out.println(f(7, 3));

	}
}
