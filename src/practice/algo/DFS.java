package practice.algo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.logging.Logger;

public class DFS< T > {
  private static final Logger log = Logger.getLogger(DFS.class.getName());

  private void dfsIterative(T root, Graph< T > graph, Map< T, Boolean > isVisited) {
    Stack< T > stack = new Stack<>();
    stack.push(root);

    while (!stack.isEmpty()) {
      T top = stack.pop();
      isVisited.put(top, Boolean.TRUE);
      log.info(top.toString());
      List< T > neighbours = graph.getNeighbours(top);
      for (T item : neighbours) {
        if (!Boolean.TRUE.equals(isVisited.get(item))) {
          stack.push(item);
        }
      }
    }
  }

  public void dfsIterative(Graph< T > graph) {
    Map< T, Boolean > isVisited = new HashMap<>();
    for (T v : graph.getVertices()) {
      if (!Boolean.TRUE.equals(isVisited.get(v))) {
        this.dfsIterative(v, graph, isVisited);
      }
    }
  }

  private void dfsRecursive(T root, Graph< T > graph, Map< T, Boolean > isVisited) {
    isVisited.put(root, Boolean.TRUE);
    log.info(root.toString());
    List< T > neighbours = graph.getNeighbours(root);
    for (T item : neighbours) {
      if (!Boolean.TRUE.equals(isVisited.get(item))) {
        dfsRecursive(item, graph, isVisited);
      }
    }
  }

  public void dfsRecursive(Graph< T > graph) {
    Map< T, Boolean > isVisited = new HashMap<>();
    for (T v : graph.getVertices()) {
      if (!Boolean.TRUE.equals(isVisited.get(v))) {
        this.dfsRecursive(v, graph, isVisited);
      }
    }
  }

  public static Graph< Integer > getRandomGraph(int n) {
    Graph< Integer > graph = new Graph<>();
    for (int i = 0; i < n; ++i) {
      int x = (int) (Math.random() * n);
      int y = (int) (Math.random() * n);
      graph.insertEdge(x, y);
      graph.insertEdge(y, x);
    }
    return graph;
  }

  public static void main(String args[]) {
    Graph< Integer > randomGraph = DFS.getRandomGraph(10);
    DFS< Integer > dfs = new DFS<>();
    dfs.dfsIterative(randomGraph);
    dfs.dfsRecursive(randomGraph);
  }
}
