package fb;

public class AllSubSet {

	public static void subSet(Integer... input) {
		int n = input.length - 1;
		for (int i = 0; i < (1 << n); ++i) {
			for (int j = 0; j < n; ++j) {
				if ((i & (1 << j)) > 0) {
					System.out.print(input[j] + ", ");
				}
			}
			System.out.println();
		}
	}
}
