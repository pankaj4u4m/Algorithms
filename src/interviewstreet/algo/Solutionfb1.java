import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solutionfb1 {

    /**
     * valid posfix notation
     * 
     * @param args
     * @throws IOException
     * @throws NumberFormatException
     */
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; ++i) {
            char[] array = br.readLine().trim().toCharArray();
            int len = array.length;
            int res = optimized(array, len);
            System.out.println(res);
        }
    }

    private static int optimized(char[] array, int len) {
        int star = 0;
        int x = 0;
        int res = 0;
        for (int i = 0; i < len; ++i) {
            if (array[i] == 'x') {
                x++;
            } else if (array[i] == '*') {
                star++;
                if (x - star < 1) {
                    star--;
                    x++;
                    array[i] = 'x';
                    res++;
                }
            }
        }

        int v = x - star - 1;
        return res + ((v) / 2);
    }
}
