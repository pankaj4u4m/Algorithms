package practice.algo;

public class LinkedList< T > {
  private Node< T > first;
  private Node< T > last;

  public LinkedList() {
    first = null;
    last = null;
  }

  public void add(T v) {
    if (last == null) {
      first = last = new Node<>(v);
    } else {
      Node< T > next = new Node<>(v);
      last.next = next;
      last = next;
    }
  }

  public void addFront(T v) {
    if (first == null) {
      last = first = new Node<>(v);
    } else {
      Node< T > previous = new Node<>(v);
      previous.next = first;
      first = previous;
    }
  }

  public T removeFirst() {
    if (first == null) {
      return null;
    } else if (first == last) {
      Node< T > top = first;
      first = last = null;
      return top.value;
    } else {
      Node< T > top = first;
      first = first.next;
      return top.value;
    }
  }

  public T first() {
    if (first == null) {
      return null;
    }
    return first.value;
  }

  public boolean isEmpty() {
    return first == null;
  }

  class Node< E > {
    private final E value;
    private Node< E > next;

    public Node(E v) {
      this.value = v;
      next = null;
    }
  }
}
