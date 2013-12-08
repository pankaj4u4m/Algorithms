import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BoxTower {

    static class Cube implements Comparable<Cube> {
        Set<Integer> visited;
        int index;
        int a;
        int b;
        int c;

        public Cube(int i, int x, int y, int z) {
            visited = new HashSet<>();
            visited.add(i);
            index = i;
            a = x;
            b = y;
            c = z;
        }

        @Override
        public int compareTo(Cube o) {

            if (b != o.b) {
                return b - o.b;
            }
            if (c != o.c) {
                return c - o.c;
            }
            if (a != o.a) {
                return a - o.a;
            }
            return 0;
        }

        @Override
        public String toString() {
            return "Cube [visited=" + visited + ", index=" + index + ", a=" + a + ", b=" + b + ", c=" + c + "]\n";
        }

    }

    /**
     * @param args
     * @throws IOException
     * @throws NumberFormatException
     */
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        List<Cube> rotationCubes = new ArrayList<>();

        for (int i = 0; i < n; ++i) {
            String[] input = br.readLine().trim().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);
            int c = Integer.parseInt(input[2]);

            rotationCubes.add(new Cube(i, a, b, c));
            rotationCubes.add(new Cube(i, a, c, b));
            rotationCubes.add(new Cube(i, b, a, c));
            rotationCubes.add(new Cube(i, b, c, a));
            rotationCubes.add(new Cube(i, c, a, b));
            rotationCubes.add(new Cube(i, c, b, a));

        }

        Collections.sort(rotationCubes);
        // System.out.println(rotationCubes);

        int sz = rotationCubes.size();

        int[] mem = new int[sz + 5];

        for (int i = 0; i < sz; ++i) {
            Cube cube = rotationCubes.get(i);
            int r = 0;
            Set<Integer> v = null;
            for (int j = 0; j < i; ++j) {
                Cube x = rotationCubes.get(j);
                if (!x.visited.contains(cube.index)) {
                    if (x.b <= cube.b && x.c <= cube.c) {
                        if (r < mem[j]) {
                            v = x.visited;
                            r = mem[j];
                        }
                    }
                }
            }
            mem[i] = r + cube.a;
            if (v != null) {
                cube.visited.addAll(v);
            }
        }

        // System.out.println(rotationCubes);

        int mx = 0;
        for (int i = 0; i < sz; ++i) {
            mx = Math.max(mx, mem[i]);
        }
        System.out.println(mx);

    }
}
