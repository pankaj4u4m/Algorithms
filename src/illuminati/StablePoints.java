package illuminati;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class StablePoints {

    private static final double eps = 1e-6;

    /**
     * @param args
     * @throws IOException
     * @throws NumberFormatException
     */
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCases = Integer.parseInt(br.readLine());
        for (int t = 0; t < testCases; ++t) {
            String input[] = br.readLine().trim().split(" ");
            int n = Integer.parseInt(input[0]);
            List<Integer> position = new ArrayList<>();
            List<Integer> mass = new ArrayList<>();
            for (int i = 1; i < input.length; i += 2) {
                position.add(Integer.parseInt(input[i]));
                mass.add(Integer.parseInt(input[i + 1]));
            }
            calculateStablepoints(n, position, mass);
        }
    }

    private static void calculateStablepoints(int n, List<Integer> position, List<Integer> mass) {
        for (int p = 0; p < n - 1; ++p) {
            double point = getStablePoint(n, position, mass, p);
            System.out.printf("%6f ", point);
        }
        System.out.println();
    }

    private static double getStablePoint(int n, List<Integer> position, List<Integer> mass, int p) {
        double start = position.get(p);
        double end = position.get(p + 1);
        double leftMassByDist = 0;
        double rightMassByDist = 0;
        while (start <= end) {
            double mid = start + (end - start) / 2;
            leftMassByDist = getMassByDist(position, mass, 0, p, mid);
            rightMassByDist = getMassByDist(position, mass, p + 1, n - 1, mid);
            if (leftMassByDist < rightMassByDist) {
                end = mid - eps;
            } else {
                start = mid + eps;
            }
        }
        return start;
    }

    private static double getMassByDist(List<Integer> position, List<Integer> mass, int s, int e, double x) {
        double v = 0;
        for (int i = s; i <= e; ++i) {
            v += mass.get(i) / sqr((position.get(i) - x));
        }
        return v;
    }

    private static double sqr(double d) {
        return d * d;
    }
}
