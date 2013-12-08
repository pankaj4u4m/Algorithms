import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class SolutionT1 {

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        List<String> dict = new ArrayList<>();
        String d = br.readLine().trim();
        while (!"//secret".equals(d)) {
            dict.add(d);
            d = br.readLine().trim();
        }
        String x = br.readLine().trim();
        int len = dict.size();
        while (x != null && !x.isEmpty()) {
            String[] input = x.split(" ");
            List<Map<Character, Character>> valid = null;
            for (int w = 0; w < input.length; ++w) {
                List<Map<Character, Character>> temp = new ArrayList<>();
                for (int i = 0; i < len; ++i) {
                    Map<Character, Character> validCypher = validCypher(input[w], dict.get(i));
                    if (validCypher != null) {
                        temp.add(validCypher);
                    }
                }

                // DEBUG
                // System.out.print(input[w]);
                // System.out.println(temp);
                if (valid == null) {
                    valid = temp;
                } else {
                    List<Map<Character, Character>> newValid = new ArrayList<>();
                    for (Map<Character, Character> v : valid) {
                        for (Map<Character, Character> t : temp) {
                            Map<Character, Character> vv = getValidated(v, t);
                            if (vv != null) {
                                newValid.add(vv);
                            }
                        }
                    }
                    valid = newValid;
                }
                // System.out.println("v" + valid);
            }
            // System.out.println("v" + valid);
            System.out.print(x + " = ");
            // int sz = valid.size();
            for (int w = 0; w < input.length - 1; ++w) {
                for (int i = 0; i < len; ++i) {
                    if (validCypher(input[w], dict.get(i), valid.get(0))) {
                        System.out.print(dict.get(i) + " ");
                        break;
                    }
                }
            }
            for (int i = 0; i < len; ++i) {
                if (validCypher(input[input.length - 1], dict.get(i), valid.get(0))) {
                    System.out.print(dict.get(i));
                    break;
                }
            }
            System.out.println();

            x = br.readLine();
            if (x != null) {
                x = x.trim();
            }
        }
    }

    private static boolean validCypher(String word, String string, Map<Character, Character> ch) {
        return helper(word, string, ch) != null;
    }

    private static Map<Character, Character> getValidated(Map<Character, Character> v, Map<Character, Character> t) {
        Map<Character, Character> nw = new HashMap<>(t);
        for (Entry<Character, Character> entry : v.entrySet()) {
            if (nw.get(entry.getKey()) == null) {
                if (nw.values().contains(entry.getValue())) {
                    return null;
                }
                nw.put(entry.getKey(), entry.getValue());
            } else if (!nw.get(entry.getKey()).equals(entry.getValue())) {
                return null;
            }

        }
        return nw;
    }

    private static Map<Character, Character> validCypher(String word, String string) {
        Map<Character, Character> ch = new HashMap<>();
        return helper(word, string, ch);
    }

    private static Map<Character, Character> helper(String word, String string, Map<Character, Character> ch) {
        int l = word.length();
        int m = string.length();
        if (l != m) {
            return null;
        }
        for (int i = 0; i < l; ++i) {
            Character character = ch.get(word.charAt(i));

            if (character == null) {
                ch.put(word.charAt(i), string.charAt(i));
            } else if (!character.equals(string.charAt(i))) {
                return null;
            }
        }
        return ch;
    }

}
