package practice.algo;

public class MyQueue< E > {
  LinkedList< E > list;

  public MyQueue() {
    list = new LinkedList<>();
  }

  public void push(E e) {
    list.add(e);
  }

  public E pop() {
    return list.removeFirst();
  }

  public E front() {
    return list.first();
  }

  public boolean isEmpty() {
    return list.isEmpty();
  }
}
