package fb;

public class SquareRoot {

	public static Double squareRoot(final double number) {
		if (number < 0) {
			return null;
		}
		double s;
		double e;
		if (number < 1) {
			s = number;
			e = 1;
		} else {
			s = 1;
			e = number;
		}
		while (s <= e) {
			final double mid = (s + e) / 2;
			if (Math.abs(mid * mid - number) < 1e-8) {
				return mid;
			}
			if (mid * mid > number) {
				e = mid - 1e-6;
			} else {
				s = mid + 1e-6;
			}
		}
		return s;

	}

	public static Double squareRoot2(final double number) {
		if (number < 0) {
			return null;
		}
		double x = number;
		double y = 1;
		while (Math.abs(x - y) > 1e-8) {
			x = (x + y) / 2;
			y = number / x;
		}
		return x;
	}

	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		System.out.println(squareRoot(.3));
		System.out.println(squareRoot2(.3));
		System.out.println(squareRoot(9));
		System.out.println(squareRoot2(9));

	}

}
