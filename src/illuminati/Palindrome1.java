package illuminati;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Palindrome1 {

    /**
     * @param args
     * @throws IOException
     * @throws NumberFormatException
     */
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCases = Integer.parseInt(br.readLine());
        for (int t = 0; t < testCases; ++t) {
            String number = br.readLine();
            String palin = new Palindrome(number.toCharArray()).nextPalindrome();
            System.out.println(palin);
        }
    }

}
