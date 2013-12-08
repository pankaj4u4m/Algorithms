// package practice.algo;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.TreeSet;
//
//public class Dijkstra< T > {
//  public Integer djkstra(T start, T destination, Graph< T > graph) {
//    TreeSet< Pair< Integer, T > > priorityQueue = new TreeSet<>();
//    Map< T, Boolean > isVisited = new HashMap<>();
//    priorityQueue.add(Pair.of(0, start));
//    if (!priorityQueue.isEmpty()) {
//      Pair< Integer, T > top = priorityQueue.pollFirst();
//      isVisited.put(top.getSecond(), Boolean.TRUE);
//      if (top.getSecond().equals(destination)) {
//        return top.getFirst();
//      }
//      for (T v : graph.getNeighbours(top.getSecond())) {
//        priorityQueue.add(Pair.of(top.getFirst() + 1, v));
//      }
//    }
//    return -1;
//  }
//}
