package illuminati;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sequence2 {

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
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);
            int a0 = Integer.parseInt(input[2]);
            int a1 = Integer.parseInt(input[3]);
            int n = Integer.parseInt(input[4]);
            System.out.println("Case " + (t + 1) + ": " + new Sequence(a, b, a0, a1).nthTerm2(n));
        }
    }

}
