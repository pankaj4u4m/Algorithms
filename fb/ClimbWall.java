package fb;

public class ClimbWall {

	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		final int n = 5;
		int a = 1;
		int b = 0;
		int c = 0;
		int r = 0;
		for (int i = 1; i <= n; ++i) {
			r = 0;
			if (i - 1 >= 0) {
				r = a;
			}
			if (i - 2 >= 0) {
				r += b;
			}
			if (i - 3 >= 0) {
				r += c;
			}
			c = b;
			b = a;
			a = r;
		}
		System.out.println(r);

	}

}
