package practice.algo;

import java.util.ArrayList;
import java.util.List;

public class Node< T > {
  private final T value;

  private final List< Node< T >> neighbours;

  public Node(T v) {
    value = v;
    neighbours = new ArrayList<>();
  }

  public T getValue() {
    return value;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((neighbours == null) ? 0 : neighbours.hashCode());
    result = prime * result + ((value == null) ? 0 : value.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof Node)) {
      return false;
    }
    Node other = (Node) obj;
    if (neighbours == null) {
      if (other.neighbours != null) {
        return false;
      }
    } else if (!neighbours.equals(other.neighbours)) {
      return false;
    }
    if (value == null) {
      if (other.value != null) {
        return false;
      }
    } else if (!value.equals(other.value)) {
      return false;
    }
    return true;
  }

}
