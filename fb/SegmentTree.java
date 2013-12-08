package fb;

public class SegmentTree {

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

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
