package fb;

public class TriceRepeat {

	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		final int B[] = { 1, 1, 1, 3, 3, 3, 20, 4, 4, 4 };
		int ones = 0;
		int twos = 0;
		int threes = 0;

		for (int i = 0; i < 10; i++) {
			twos |= ones & B[i];
			ones ^= B[i];
			threes = (ones & twos);
			ones &= ~threes;
			twos &= ~threes;
		}

		System.out.printf("\n unique element one= %d, two = %d, trice=%d\n",
				ones, twos, threes);
	}

}
