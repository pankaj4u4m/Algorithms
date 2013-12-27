//package fb;

import java.util.ArrayList;
import java.util.List;

public class KMPStringMatch {

	public static List<Integer> match(final String string, final String pattern) {
		final List<Integer> matchIndex = new ArrayList<Integer>();
		final int n = string.length();
		final int m = pattern.length();
		pattern.length();
		final List<Integer> prefix = computePrefix(pattern);
		for(Integer i: prefix){
			System.out.print(i + " ");
		}
		int k = 0;
		for (int i = 0; i < n; ++i) {
			while ((k > 0) && (pattern.charAt(k ) != string.charAt(i))) {
				k = prefix.get(k);
			}
			if (pattern.charAt(k ) == string.charAt(i)) {
				k += 1;
			}
			if (k == m) {
				System.out.println(i - m);
				k = 0;
			}
		}
		return matchIndex;
	}

	public static List<Integer> computePrefix(final String pattern) {
		final int n = pattern.length();
		final List<Integer> prefix = new ArrayList<Integer>(n + 1);
		prefix.add(0);
		prefix.add(0);
		int k = 0;
		System.out.println(pattern.replaceAll("", " "));

		for (int i = 1; i < n; ++i) {
			while ((k > 0) && (pattern.charAt(k) != pattern.charAt(i))) {
				k = prefix.get(k);
			}
			if (pattern.charAt(k) == pattern.charAt(i)) {
				k = k + 1;
			}

			prefix.add(k);
		}
		return prefix;
	}

	public static void main(final String[] args) {
		final String string = "pankajkumarxxkuxmarkumarxx;";
		final String pattern = "kumar";
		match(string, pattern);
	}
}
