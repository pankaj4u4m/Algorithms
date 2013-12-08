package fb;

class LinkListNode {
	int data;

	/**
	 * @return the data
	 */
	public int getData() {
		return this.data;
	}

	private LinkListNode next;

	public LinkListNode(final int data) {
		this.data = data;
		this.next = null;
	}

	public void setNext(final LinkListNode next) {
		this.next = next;
	}

	public LinkListNode getNext() {
		return this.next;
	}
}

public class ReverseLinkedList {

	public static void main(final String[] args) {
		final int a[] = { 1, 2, 3, 4, 5, 6, 7, 8 };
		LinkListNode root = new LinkListNode(1);
		LinkListNode next = root;
		for (int i = 1; i < a.length; ++i) {
			next.setNext(new LinkListNode(a[i]));
			next = next.getNext();
		}
		root = reverseLinedList(root);
		while (root != null) {
			System.out.print(root.getData() + ", ");
			root = root.getNext();
		}
	}

	private static LinkListNode reverseLinedList(final LinkListNode root) {
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
}
