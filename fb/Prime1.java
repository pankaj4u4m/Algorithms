package fb;

import java.util.ArrayList;
import java.util.List;

public class Prime1 {

	public static void prime(int L, int U) {
		if (L < 0 || U < 0 || L < U) {
			return;
		}
		int d = U - L + 1;
		List<Boolean> flag = new ArrayList<Boolean>(d + 1);
		for (int i = 0; i < d; ++i) {
			flag.set(i, true);
		}
		for (int i = L % 2; i < d; i += 2) {
			flag.set(i, false);
		}
		for (int i = 3, _s = (int) Math.floor(Math.sqrt(U)); i < _s; i += 2) {
			if (i > L && flag.get(i - L) == false) {
				continue;
			}
			int t = L / i * i;
			if (t < L) {
				t += i;
			}
			if (i > L) {
				for (int j = t - L + i; j < d; j += 1) {
					flag.set(j, false);
				}
			} else {
				for (int j = t - L; j < d; j += i) {
					flag.set(j, false);
				}
			}
		}
		if (L <= 1)
			flag.set(1 - L, false);
		if (L <= 2)
			flag.set(2 - L, true);
		for (int i = 0; i < d; ++i) {
			if (flag.get(i) == true) {
				System.out.println(L + i);
			}
		}
	}
}
