import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution1 {

    public static void main(String[] args) throws IOException {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().trim().split(" ");
        int n = Integer.parseInt(input[0]);
        int k = Integer.parseInt(input[1]);

        if (n == 0) {
            System.out.println("0");
            System.exit(0);
        }
        String[] numbers = br.readLine().trim().split(" ");
        List<Integer> nums = new ArrayList<>();
        for (String no : numbers) {
            nums.add(Integer.parseInt(no));
        }
        Collections.sort(nums);
        int diff = 0;
        int x = 0;
        for (int i = 1; i < n;) {
            if (x < i && (nums.get(i) - nums.get(x)) == k) {
                diff++;
                ++i;
            } else if (x < i && (nums.get(i) - nums.get(x)) > k) {
                ++x;
            } else {
                ++i;
            }
        }
        System.out.println(diff);
    }
}