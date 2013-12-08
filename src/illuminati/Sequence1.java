package illuminati;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sequence1 {

    /**
     * @param args
     * @throws IOException
     * @throws NumberFormatException
     */
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCases = Integer.parseInt(br.readLine());
        for (int t = 0; t < testCases; ++t) {
            String[] input = br.readLine().trim().split(" ");
            int n = Integer.parseInt(input[0]);
            int p = Integer.parseInt(input[1]);
            int q = Integer.parseInt(input[2]);
            System.out.println("Case " + (t + 1) + ": " + new Sequence(p, q, 1).nthTerm1(n));
        }
    }

}
