package practice.algo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph< T > {
  private final Map< T, List< T >> graph;

  public Graph() {
    graph = new HashMap<>();
  }

  public Map< T, List< T >> getGraph() {
    return graph;
  }

  public void insertEdge(T from, T to) {
    List< T > list = graph.get(from);
    if (list == null) {
      list = new ArrayList<>();
      graph.put(from, list);
    }
    list.add(to);
  }

  public List< T > getNeighbours(T node) {
    List< T > list = graph.get(node);
    if (list == null) {
      return Collections.emptyList();
    }
    return Collections.unmodifiableList(list);
  }

  public List< T > getVertices() {
    return Collections.unmodifiableList(new ArrayList<>(graph.keySet()));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((graph == null) ? 0 : graph.hashCode());
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
    if (!(obj instanceof Graph)) {
      return false;
    }
    Graph other = (Graph) obj;
    if (graph == null) {
      if (other.graph != null) {
        return false;
      }
    } else if (!graph.equals(other.graph)) {
      return false;
    }
    return true;
  }

}
