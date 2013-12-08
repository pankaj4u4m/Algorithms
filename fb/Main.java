/**
 * 
 */
package fb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author kpankaj
 * 
 */
public class Main {

	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 10; ++i) {
			list.add(i);
		}
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < 6; ++i) {
			result.add(0);
		}
		printAllSubSet(list, result, 0, 0, 2, 10);
	}

	private static void printAllSubSet(ArrayList<Integer> list, ArrayList<Integer> result, int j, int p, int k, int n) {
		if (j == k) {
			for (int i = 0; i < k; ++i) {
				System.out.print(result.get(i));
			}
			System.out.println();
		} else {
			for (int i = p; i < n; ++i) {
				result.set(j, i);
				printAllSubSet(list, result, j + 1, i + 1, k, n);
			}
		}
		char cha[] = { 'a', 'b' };
	}

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

	public static void bfs(Map<Integer, List<Integer>> graph) {
		Map<Integer, Boolean> isVisited = new HashMap<Integer, Boolean>();
		Queue<Pair<Integer, Integer>> queue = new PriorityQueue<Pair<Integer, Integer>>();

		queue.add(new Pair<Integer, Integer>(0, 0));
		while (!queue.isEmpty()) {
			Pair<Integer, Integer> top = queue.poll();
			Integer v = top.getFirst();
			Integer d = top.getSecond();
			for (Integer neighbour : graph.get(v)) {
				Boolean val = isVisited.get(neighbour);
				if (val == null || !val) {
					isVisited.put(neighbour, true);
					queue.add(new Pair<Integer, Integer>(neighbour, d + 1));
				}
			}
		}
	}

	public static int modulo(int a, int b, int c) {
		long x = 1, y = a;
		while (b > 0) {
			if ((b & 1) == 1) {
				y = (x * y) % c;
			}
			y = (y * y) % c;
			b >>= 1;
		}
		return (int) y % c;
	}

	public static void climbWall(final String[] args) {
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

	private static Map<Integer, Boolean> isVisited;
	static {
		isVisited = new HashMap<Integer, Boolean>();
	}

	public static void dfs(Map<Integer, List<Integer>> graph) {
		if (graph == null || graph.isEmpty()) {
			return;
		}
		for (Entry<Integer, List<Integer>> entry : graph.entrySet()) {
			Boolean val = isVisited.get(entry.getKey());
			if (val == null || !val) {
				isVisited.put(entry.getKey(), true);
				dfsVisit(entry.getKey(), graph);
			}
		}
	}

	private static void dfsVisit(Integer key, Map<Integer, List<Integer>> graph) {
		for (Integer entry : graph.get(key)) {
			Boolean val = isVisited.get(entry);
			if (val == null || !val) {
				isVisited.put(entry, true);
				dfsVisit(entry, graph);
			}
		}

	}

	public static List<Integer> match(final String string, final String pattern) {
		final List<Integer> matchIndex = new ArrayList<Integer>();
		final int n = string.length();
		final int m = pattern.length();
		pattern.length();
		final List<Integer> prefix = computePrefix(pattern);
		int k = 0;
		for (int i = 1; i < n; ++i) {
			while ((k > 0) && (pattern.charAt(k + 1 - 1) != string.charAt(i - 1))) {
				k = prefix.get(k);
			}
			if (pattern.charAt(k + 1 - 1) == string.charAt(i - 1)) {
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

		for (int i = 2; i <= n; ++i) {
			while ((k > 0) && (pattern.charAt(k + 1 - 1) != pattern.charAt(i - 1))) {
				k = prefix.get(k);
			}
			if (pattern.charAt(k + 1 - 1) == pattern.charAt(i - 1)) {
				k = k + 1;
			}

			prefix.add(k);
		}
		return prefix;
	}

	public static int knapsack(final int c, final int w[], final int n, final int m[][]) {
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

	public static Integer kthSmallestInTwoSorterArray(int a[], int b[], int n, int m, int k) {
		if (m + n < k)
			return null;
		if (n + m == k)
			return Math.max(a[n - 1], b[m - 1]);
		if (n > m) {
			int t[] = a;
			a = b;
			b = t;
			int c = n;
			n = m;
			m = c;
		}
		if (n == 0)
			return b[k - 1];
		int i = Math.min(n, k / 2);
		int j = k - i;
		int steps = i / 4;
		boolean f = true;
		while (steps > 0) {
			System.out.println(steps + " = " + i + " : " + j);
			f = true;
			if (a[i - 1] > b[j - 1]) {
				// if (i > 0){
				i -= steps;
				f = false;
				// }
				// if (j < m){
				j += steps;
				f = true;
				// }
			} else {
				// if (i < n){
				i += steps;
				f = false;
				// }
				// if (j > 0){
				j -= steps;
				f = true;
				// }
			}
			if (f)
				steps >>= 1;
		}
		System.out.println(steps + " = " + i + " : " + j);
		return Math.max(a[i - 1], b[j - 1]);
	}

	public static int nChairsRemoveDthChairEachTime(final int n, final int k) {
		int x;
		if (n == 1) {
			x = 1;
		} else {
			x = (nChairsRemoveDthChairEachTime(n - 1, k) + k - 1) % n + 1;
		}
		System.out.println(n + " : " + k + " : " + x);
		return x;
	}

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

	public static void compile(final String pattern, final Node head, int[][] TF) {
		final int n = pattern.length();
		final Node[] index = new Node[n + 2];
		index[0] = head;
		for (int i = 0; i <= n; ++i) {
			index[i + 1] = new Node(i + 1);
		}
		int lps = 0;

		TF[0][pattern.charAt(0)] = 1;
		if (pattern.charAt(0) == '*') {
			for (char x = 0; x < 256; ++x) {
				TF[0][x] = 1;
			}
		}
		boolean skip = false;
		for (int i = 1; i <= n; ++i) {
			for (int x = 0; x < 256; x++) {
				TF[i][x] = TF[lps][x];
			}
			// Update the entry corresponding to this character
			if (i < n) {
				if (pattern.charAt(i) == '*') {
					for (char x = 0; x < 256; ++x) {
						TF[i][x] = i + 1;
					}
					if (i + 1 < n) {
						TF[i][pattern.charAt(i + 1)] = i + 2;
						// ++i;
					}
					lps = i;
					skip = true;
				} else {
					TF[i][pattern.charAt(i)] = i + 1;
					if (!skip) {
						lps = TF[lps][pattern.charAt(i)];
						skip = !skip;
					}
				}
			}
			// Update lps for next row to be filled
		}
		for (int i = 0; i <= n; ++i) {
			for (char x = 0; x < 256; ++x) {
				index[i].getNode().put(x, index[TF[i][x]]);
			}
			if (i < n) {
				if (pattern.charAt(i) == '.') {
					for (char x = 0; x < 256; ++x) {
						index[i].getNode().put(x, index[i + 1]);
					}
				}
			}
		}
	}

	public static boolean search(final String text, String pattern) {
		pattern = preprocess(pattern);
		final int m = pattern.length();
		final int n = text.length();
		final Node head = new Node(0);
		final int[][] TF = new int[m + 1][256];
		compile(pattern, head, TF);

		int j = 0;
		Node next = head;
		for (int i = 0; i < n; ++i) {
			next = next.getNode().get(text.charAt(i));
			System.out.print(next.getN() + "->");
			if (next.getN() == m && i == n - 1) {
				return true;
			}
		}
		return false;
	}

	private static String preprocess(String pattern) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < pattern.length(); ++i) {
			if (pattern.charAt(i) == '+') {
				buffer.append(".*");
			} else {
				buffer.append(pattern.charAt(i));
			}
		}
		return buffer.toString();
	}

	public static LinkListNode reverseLinedList(final LinkListNode root) {
		if (root == null) {
			return null;
		}
		if (root.getNext() == null) {
			return null;
		}
		LinkListNode last = root;
		LinkListNode current = root.getNext();
		LinkListNode next = current.getNext();
		last.setNext(null);
		while (current != null) {

			current.setNext(last);
			last = current;
			current = next;
			if (current != null) {
				next = current.getNext();
			}
		}
		return last;
	}

	public static void init(int p, int x, int y, int tree[], int a[]) {
		if (x == y) {
			tree[p] = x;
		} else {
			init(2 * p, x, (x + y) / 2, tree, a);
			init(2 * p + 1, (x + y) / 2 + 1, y, tree, a);
			if (a[tree[2 * p]] <= a[tree[2 * p + 1]]) {
				tree[p] = tree[2 * p];
			} else {
				tree[p] = tree[2 * p + 1];
			}
		}
	}

	int query(int p, int x, int y, int tree[], int a[], int i, int j) {
		if (i > y || j < x) {
			return -1;
		}
		if (i <= x && y <= j) {
			return tree[p];
		}
		int t1 = query(2 * p, x, (x + y) / 2, tree, a, i, j);
		int t2 = query(2 * p + 1, (x + y) / 2 + 1, y, tree, a, i, j);
		if (t1 == -1) {
			return tree[p] = t2;
		}
		if (t2 == -1) {
			return tree[p] = t1;
		}
		if (a[t1] <= a[t2]) {
			return tree[p] = t2;
		}
		return tree[p] = t1;
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

	public static void printSubset(final int[] a, final int[] b, final int s, final int k) {
		if (k < 0) {
			return;
		}
		if (k == 0) {
			for (int i = s; i < a.length; ++i) {
				for (int j = b.length - 1; j > 0; --j) {
					System.out.print(b[j]);
				}
				System.out.println(a[i]);
			}
		}
		for (int i = s; i < a.length - k; ++i) {
			b[k] = a[i];
			printSubset(a, b, i + 1, k - 1);
		}
	}

	public static void triceRepeat() {
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

		System.out.printf("\n unique element one= %d, two = %d, trice=%d\n", ones, twos, threes);
	}

}
