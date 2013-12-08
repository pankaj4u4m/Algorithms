import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

public class Solution {

    enum Direction {
        LEFT, RIGHT, DOWN, UP;
    }

    static class ColDirection {
        Set<Integer> up = new HashSet<>();
        Set<Integer> down = new HashSet<>();
        Map<Integer, Integer> ucrosspoint = new HashMap<>();
        Map<Integer, Integer> dcrosspoint = new HashMap<>();
        Map<Integer, Integer> uppoint = new HashMap<>();
        Map<Integer, Integer> downpoint = new HashMap<>();

        int hashcode = 0;

        @Override
        public String toString() {
            return "ColDirection [left=" + up + ", right=" + down + ", lcrosspoint=" + ucrosspoint + ", rcrosspoint="
                    + dcrosspoint + ", leftpoint=" + uppoint + ", rightpoint=" + downpoint + "]";
        }

        @Override
        public int hashCode() {
            if (hashcode > 0) {
                return hashcode;
            }
            int result = 1;
            result = result + ((ucrosspoint == null) ? 0 : ucrosspoint.hashCode());
            result = result + ((up == null) ? 0 : up.hashCode());
            result = result + ((uppoint == null) ? 0 : uppoint.hashCode());
            result = result + ((dcrosspoint == null) ? 0 : dcrosspoint.hashCode());
            result = result + ((down == null) ? 0 : down.hashCode());
            result = result + ((downpoint == null) ? 0 : downpoint.hashCode());
            hashcode = result;
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
            if (!(obj instanceof ColDirection)) {
                return false;
            }
            ColDirection other = (ColDirection) obj;
            if (ucrosspoint == null) {
                if (other.ucrosspoint != null) {
                    return false;
                }
            } else if (!ucrosspoint.equals(other.ucrosspoint)) {
                return false;
            }
            if (up == null) {
                if (other.up != null) {
                    return false;
                }
            } else if (!up.equals(other.up)) {
                return false;
            }
            if (uppoint == null) {
                if (other.uppoint != null) {
                    return false;
                }
            } else if (!uppoint.equals(other.uppoint)) {
                return false;
            }
            if (dcrosspoint == null) {
                if (other.dcrosspoint != null) {
                    return false;
                }
            } else if (!dcrosspoint.equals(other.dcrosspoint)) {
                return false;
            }
            if (down == null) {
                if (other.down != null) {
                    return false;
                }
            } else if (!down.equals(other.down)) {
                return false;
            }
            if (downpoint == null) {
                if (other.downpoint != null) {
                    return false;
                }
            } else if (!downpoint.equals(other.downpoint)) {
                return false;
            }
            return true;
        }

    }

    /**
     * @param args
     * @throws IOException
     */
    static int row;
    static int col;
    static int[][] maze;

    public static void main(String[] args) throws IOException {
        scan();
        // generate();
        long t = System.nanoTime();
        Long res = findWays();
        // System.out.println(ways);
        // System.out.println((System.nanoTime() - t) / 1000000.0);
        System.out.println(res);
    }

    private static void generate() throws IOException {
        BufferedWriter bw =
                new BufferedWriter(new FileWriter("C:\\Users\\kpankaj\\Dropbox\\src\\practice\\quora.txt"));
        col = 9;
        row = 10;
        bw.write(col + " " + row + "\n");
        Random r = new Random();
        maze = new int[row][col];
        boolean start = true;
        boolean end = true;
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                if (start && r.nextBoolean()) {
                    maze[i][j] = 2;
                    start = false;
                } else if (end && r.nextBoolean()) {
                    maze[i][j] = 3;
                    end = false;
                } else {
                    maze[i][j] = 0;
                }

                bw.write(maze[i][j] + " ");
            }
            bw.write("\n");
        }
        bw.flush();
    }

    private static void scan() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] rowcol = br.readLine().trim().split(" ");
        col = Integer.parseInt(rowcol[0]);
        row = Integer.parseInt(rowcol[1]);
        maze = new int[row][col];
        for (int i = 0; i < row; ++i) {
            String[] r = br.readLine().trim().split(" ");
            for (int j = 0; j < col; ++j) {
                int v = r[j].charAt(0) - '0';
                maze[i][j] = v;
            }
        }
    }

    @SuppressWarnings("unchecked")
    private static Long findWays() {
        Map<ColDirection, Long> start = new HashMap<>();
        List<ColDirection> ways = allWays(0, 0, new Set[col], new HashSet<Integer>());
        for (ColDirection c : ways) {
            Long integer = start.get(c);
            if (integer == null) {
                integer = 0L;
            }
            start.put(c, integer + 1);
        }
        for (int i = 1; i < row; ++i) {
            Map<ColDirection, Long> temp = new HashMap<>();
            // System.out.println(start);
            Map<Set<Integer>, List<ColDirection>> cache = new HashMap<>();
            for (Entry<ColDirection, Long> up : start.entrySet()) {
                if (up.getKey().down.isEmpty()) {
                    continue;
                }
                List<ColDirection> allWays = cache.get(up.getKey().down);
                if (allWays == null) {
                    allWays = allWays(i, 0, new Set[col], up.getKey().down);
                    cache.put(up.getKey().down, allWays);
                }

                for (ColDirection down : allWays) {
                    if (!isCycle(up.getKey(), down)) {
                        ColDirection c = new ColDirection();
                        c.down = down.down;
                        populatePoint(up.getKey(), down, c);
                        Long integer = temp.get(c);
                        if (integer == null) {
                            integer = 0L;
                        }
                        temp.put(c, integer + up.getValue());
                        // System.out.println("l=" + left.getKey() + "\nr=" + right.getKey() + "\nc=" + c);
                    }
                }
            }
            start = temp;
        }
        long res = 0;

        for (Entry<ColDirection, Long> e : start.entrySet()) {
            res += e.getValue();
        }
        // System.out.println(start);
        return res;
    }

    private static void populatePoint(ColDirection up, ColDirection down, ColDirection c) {
        for (Integer x : down.down) {
            Integer v;
            if (c.downpoint.get(x) != null) {
                continue;
            }
            Integer e = null;
            if ((v = down.dcrosspoint.get(x)) != null) {
                e = getDownEnd(v, up, down, true);
            } else {
                e = down.downpoint.get(x);
            }
            if (e != null) {
                c.downpoint.put(x, e);
                c.downpoint.put(e, x);
            }
        }
    }

    private static Integer getDownEnd(Integer x, ColDirection up, ColDirection down, boolean isRight) {
        Integer v;
        if (isRight) {

            if ((v = up.downpoint.get(x)) != null) {
                return getDownEnd(v, up, down, !isRight);
            } else {
                return null;
            }
        } else {
            if ((v = down.uppoint.get(x)) != null) {
                return getDownEnd(v, up, down, !isRight);
            } else {
                return down.ucrosspoint.get(x);
            }
        }
    }

    private static boolean isCycle(ColDirection up, ColDirection down) {
        for (Integer x : up.down) {
            if (isCycle(up.downpoint.get(x), up, down, false, x)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isCycle(Integer x, ColDirection up, ColDirection down, boolean isRight, long p) {
        if (x == null) {
            return false;
        }
        if (x == p) {
            return true;
        }
        if (isRight) {
            return isCycle(up.downpoint.get(x), up, down, !isRight, p);

        } else {
            return isCycle(down.uppoint.get(x), up, down, !isRight, p);
        }
    }

    private static List<ColDirection> allWays(int r, int j, Set<Direction>[] directions, Set<Integer> down) {
        if (j == col) {
            if (directions[j - 1].contains(Direction.RIGHT)) {
                return new ArrayList<>();
            } else {
                ColDirection colDirection = new ColDirection();
                for (int x = 0; x < col; ++x) {
                    loop(directions, colDirection, x);
                }
                return Collections.singletonList(colDirection);
            }
        }
        List<Set<Direction>> rowdirections = new ArrayList<>();
        if (r == 0) {
            if (j == 0 || !directions[j - 1].contains(Direction.RIGHT)) {
                if (maze[r][j] == 2 || maze[r][j] == 3) {
                    rowdirections.add(Collections.singleton(Direction.DOWN));
                    rowdirections.add(Collections.singleton(Direction.RIGHT));
                } else if (maze[r][j] == 0) {
                    rowdirections.add(new HashSet<>(Arrays.asList(Direction.DOWN, Direction.RIGHT)));
                } else {
                    rowdirections.add(new HashSet<Direction>());
                }
            } else {
                if (maze[r][j] == 2 || maze[r][j] == 3) {
                    rowdirections.add(new HashSet<Direction>());
                } else if (maze[r][j] == 0) {
                    rowdirections.add(Collections.singleton(Direction.DOWN));
                    rowdirections.add(Collections.singleton(Direction.RIGHT));
                } else {
                }
            }
        } else {
            boolean contains = down.contains(j);
            if (r == row - 1) {
                if (j == 0 || !directions[j - 1].contains(Direction.RIGHT)) {
                    if (maze[r][j] == 2 || maze[r][j] == 3) {
                        if (contains) {
                            rowdirections.add(Collections.singleton(Direction.UP));
                        } else {
                            rowdirections.add(Collections.singleton(Direction.RIGHT));
                        }
                    } else if (maze[r][j] == 0) {
                        if (contains) {
                            rowdirections.add(new HashSet<>(Arrays.asList(Direction.UP, Direction.RIGHT)));
                        }
                    } else {
                        if (contains) {

                        } else {
                            rowdirections.add(new HashSet<Direction>());
                        }
                    }
                } else {
                    if (maze[r][j] == 2 || maze[r][j] == 3) {
                        if (contains) {

                        } else {
                            rowdirections.add(new HashSet<Direction>());
                        }
                    } else if (maze[r][j] == 0) {
                        if (contains) {
                            rowdirections.add(Collections.singleton(Direction.UP));
                        } else {
                            rowdirections.add(Collections.singleton(Direction.RIGHT));
                        }
                    } else {

                    }
                }
            } else {
                if (j == 0 || !directions[j - 1].contains(Direction.RIGHT)) {
                    if (maze[r][j] == 2 || maze[r][j] == 3) {
                        if (contains) {
                            rowdirections.add(Collections.singleton(Direction.UP));
                        } else {
                            rowdirections.add(Collections.singleton(Direction.RIGHT));
                            rowdirections.add(Collections.singleton(Direction.DOWN));
                        }
                    } else if (maze[r][j] == 0) {
                        if (contains) {
                            rowdirections.add(new HashSet<>(Arrays.asList(Direction.UP, Direction.RIGHT)));
                            rowdirections.add(new HashSet<>(Arrays.asList(Direction.UP, Direction.DOWN)));
                        } else {
                            rowdirections.add(new HashSet<>(Arrays.asList(Direction.DOWN, Direction.RIGHT)));
                        }
                    } else {
                        if (contains) {

                        } else {
                            rowdirections.add(new HashSet<Direction>());
                        }
                    }
                } else {
                    if (maze[r][j] == 2 || maze[r][j] == 3) {
                        if (contains) {

                        } else {
                            rowdirections.add(new HashSet<Direction>());
                        }
                    } else if (maze[r][j] == 0) {
                        if (contains) {
                            rowdirections.add(Collections.singleton(Direction.UP));
                        } else {
                            rowdirections.add(Collections.singleton(Direction.RIGHT));
                            rowdirections.add(Collections.singleton(Direction.DOWN));
                        }
                    } else {

                    }
                }
            }
        }
        List<ColDirection> colDirections = new ArrayList<>();
        for (Set<Direction> d : rowdirections) {
            directions[j] = d;
            colDirections.addAll(allWays(r, j + 1, directions, down));
        }
        return colDirections;
    }

    private static void loop(Set<Direction>[] directions, ColDirection colDirection, int x) {
        if (directions[x].contains(Direction.UP)) {
            colDirection.up.add(x);
            int end = x;
            while (end - 1 >= 0 && directions[end - 1].contains(Direction.RIGHT)) {
                end--;
            }
            if (end != x && directions[end].contains(Direction.UP)) {
                colDirection.uppoint.put(x, end);
                colDirection.uppoint.put(end, x);
            }
            if (directions[end].contains(Direction.DOWN)) {
                colDirection.ucrosspoint.put(x, end);
                colDirection.dcrosspoint.put(end, x);
            }
        }
        if (directions[x].contains(Direction.DOWN)) {
            colDirection.down.add(x);
            int end = x;
            while (end - 1 >= 0 && directions[end - 1].contains(Direction.RIGHT)) {
                end--;
            }
            if (end != x && directions[end].contains(Direction.DOWN)) {
                colDirection.downpoint.put(x, end);
                colDirection.downpoint.put(end, x);
            }
            if (directions[end].contains(Direction.UP)) {
                colDirection.dcrosspoint.put(x, end);
                colDirection.ucrosspoint.put(end, x);
            }
        }
    }
}
