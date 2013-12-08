package fb;

import java.util.HashMap;
import java.util.Map;

class Node {
	private int n;

	private Map<Character, Node> toNode = new HashMap<Character, Node>();

	public Node(final int n) {
		super();
		this.n = n;
	}

	/**
	 * @return the toNode
	 */
	public Map<Character, Node> getNode() {
		return this.toNode;
	}

	public int getN() {
		return n;
	}

}

public class Regex {

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
					}
					lps = i;
					skip = true;
				} else if (pattern.charAt(i) == '.') {
					for (char x = 0; x < 256; ++x) {
						TF[i][x] = i + 1;
					}
					lps = i;
				} else {
					TF[i][pattern.charAt(i)] = i + 1;
					if (!skip) {
						lps = TF[lps][pattern.charAt(i)];
					} else {
						skip = false;
					}
				}
			}
			// Update lps for next row to be filled
		}
		for (int i = 0; i <= n; ++i) {
			for (char x = 0; x < 256; ++x) {
				index[i].getNode().put(x, index[TF[i][x]]);
			}
			if (i < n && pattern.charAt(i) == '.') {
				for (char x = 0; x < 256; ++x) {
					index[i].getNode().put(x, index[i + 1]);
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

	/**
	 * match * 0 or many, + one or many, . one
	 * 
	 * @param args
	 */
	public static void main(final String[] args) {
		final String[] text = { "Gee for Gks", "geeks", "test", "pankaj", "pankaj", "pankajkumar", "geeksgeekgeks",
				"lasttt", "check", "acacacd" };
		final String[] pattern = { "Ge*ks", "ge*eks", "*", "pan.aj", "pan.j", "pan*kumar", "g*ks", "la*", "ch+k",
				"aca.d" };
		for (int i = 0; i < text.length; ++i) {
			System.out.println(text[i] + " : " + pattern[i] + " = " + search(text[i], pattern[i]));
		}
	}

}
