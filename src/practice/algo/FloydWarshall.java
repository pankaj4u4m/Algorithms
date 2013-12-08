package practice.algo;

public class FloydWarshall {
  public int[][] allPairShortestPath(int distance[][]) {
    int n = distance.length;
    int m = 0;
    if (n > 0) {
      m = distance[0].length;
    }
    if (n <= 0 || m <= 0 || n != m) {
      throw new IllegalArgumentException("");
    }
    int path[][] = new int[n][n];
    for (int k = 0; k < n; ++k) {
      for (int i = 0; i < n; ++i) {
        for (int j = 0; j < n; ++j) {
          path[i][j] = Math.min(path[i][j], distance[i][k] + distance[k][j]);
        }
      }
    }

    return path;
  }
}
