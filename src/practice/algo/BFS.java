package practice.algo;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

public class BFS< T > {
  private static final Logger log = Logger.getLogger(BFS.class.getSimpleName());

  private void bfs(T root, Graph< T > graph, Map< T, Boolean > isVisited) {
    Queue< T > queue = new LinkedBlockingQueue<>();
    queue.add(root);
    while (!queue.isEmpty()) {
      T poll = queue.poll();
      isVisited.put(poll, Boolean.TRUE);
      log.info(poll.toString());
      for (T n : graph.getNeighbours(poll)) {
        if (!Boolean.TRUE.equals(isVisited.get(n))) {
          queue.add(n);
        }
      }
    }
  }

  public void bfs(Graph< T > graph) {
    Map< T, Boolean > isVisited = new HashMap<>();
    for (T v : graph.getVertices()) {
      if (!Boolean.TRUE.equals(isVisited.get(v))) {
        this.bfs(v, graph, isVisited);
      }
    }
  }

  public static void main(String[] args) {
    Graph< Integer > randomGraph = DFS.getRandomGraph(10);
    new BFS< Integer >().bfs(randomGraph);
    new DFS< Integer >().dfsIterative(randomGraph);
  }
}
