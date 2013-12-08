import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Solution {

    static class Vertex {
        String word;
        List<Vertex> children = new ArrayList<>();
        List<List<Vertex>> ladder = new ArrayList<>();
        int maxSoFar = 0;
        int score = 0;

        Vertex(String word) {
            this.word = word;
            score = getScrabbleScore(word);
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("Tree [word=");
            builder.append(word);
            builder.append(", maxSoFar=");
            builder.append(maxSoFar);
            builder.append(", score=");
            builder.append(score);
//            builder.append(", heightScore=");
//            builder.append(heightScore);
//            builder.append(", children=");
//            builder.append(children);
            builder.append("]");
            return builder.toString();
        }

        private Integer getScrabbleScore(String word) {
            int s = 0;
            for (int i = 0; i < word.length(); ++i) {
                s += points[word.charAt(i) - 'A'];
            }
            return s;
        }
    }

    static Map<String, Vertex> visited = new HashMap<>();

    static Set<String> dictionary = new HashSet<>();
    static List<List<Character>> replacements = new ArrayList<>();
    static {
        replacements.add(Arrays.asList('A', 'E', 'I', 'L', 'N', 'O', 'R', 'S', 'T', 'U'));
        replacements.add(Arrays.asList('D', 'G'));
        replacements.add(Arrays.asList('B', 'C', 'M', 'P'));
        replacements.add(Arrays.asList('F', 'H', 'V', 'W', 'Y'));
        replacements.add(Arrays.asList('K'));
        replacements.add(Arrays.asList('J', 'X'));
        replacements.add(Arrays.asList('Q', 'Z'));
    }
    static int[] index = new int[27];

    static {
        index['A' - 'A'] = 0;
        index['E' - 'A'] = 0;
        index['I' - 'A'] = 0;
        index['L' - 'A'] = 0;
        index['N' - 'A'] = 0;
        index['O' - 'A'] = 0;
        index['R' - 'A'] = 0;
        index['S' - 'A'] = 0;
        index['T' - 'A'] = 0;
        index['U' - 'A'] = 0;

        index['D' - 'A'] = 1;
        index['G' - 'A'] = 1;

        index['B' - 'A'] = 2;
        index['C' - 'A'] = 2;
        index['M' - 'A'] = 2;
        index['P' - 'A'] = 2;

        index['F' - 'A'] = 3;
        index['H' - 'A'] = 3;
        index['V' - 'A'] = 3;
        index['W' - 'A'] = 3;
        index['Y' - 'A'] = 3;

        index['K' - 'A'] = 4;

        index['J' - 'A'] = 5;
        index['X' - 'A'] = 5;

        index['Q' - 'A'] = 6;
        index['Z' - 'A'] = 6;
    }
    static int[] points = new int[27];

    static {
        points['A' - 'A'] = 1;
        points['E' - 'A'] = 1;
        points['I' - 'A'] = 1;
        points['L' - 'A'] = 1;
        points['N' - 'A'] = 1;
        points['O' - 'A'] = 1;
        points['R' - 'A'] = 1;
        points['S' - 'A'] = 1;
        points['T' - 'A'] = 1;
        points['U' - 'A'] = 1;

        points['D' - 'A'] = 2;
        points['G' - 'A'] = 2;

        points['B' - 'A'] = 3;
        points['C' - 'A'] = 3;
        points['M' - 'A'] = 3;
        points['P' - 'A'] = 3;

        points['F' - 'A'] = 4;
        points['H' - 'A'] = 4;
        points['V' - 'A'] = 4;
        points['W' - 'A'] = 4;
        points['Y' - 'A'] = 4;

        points['K' - 'A'] = 5;

        points['J' - 'A'] = 8;
        points['X' - 'A'] = 8;

        points['Q' - 'A'] = 10;
        points['Z' - 'A'] = 10;
    }

    static void generate() {
        for (int i = 0; i < 7; ++i) {
            for (int j = 7; j < 14; ++j) {
                for (int k = 14; k < 21; ++k) {
                    for (int l = 21; l < 26; ++l) {
                        char[] c =
                                new char[] { (char) (i + 'A'), (char) (j + 'A'), (char) (k + 'A'), (char) (l + 'A') };
                        String s = new String(c);
                        System.out.println(s + ":" + new Vertex(s).score);
                    }
                }
            }
        }
    }

    /**
     * @param args
     * @throws IOException
     * @throws NumberFormatException
     */
    public static void main(String[] args) throws NumberFormatException, IOException {
        // generate();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; ++i) {
            String word = br.readLine().trim();
            if (word.length() == k) {
                dictionary.add(word);
            }
        }
        List<Vertex> trees = new ArrayList<>();
        for (String word : dictionary) {
            if (visited.get(word) == null) {
                trees.add(dfsLadder(word.toCharArray()));
            }
        }
        // System.out.println(trees);
        int mx = 0;
        for (Vertex tree : trees) {
            populateTop2Height(tree);
            mx = Math.max(mx, tree.maxSoFar);
        }
//        for (Vertex v : trees) {
//            for (List<Vertex> l : v.ladder) {
//                Collections.reverse(l);
//                System.out.println(l);
//            }
//            System.out.println();
//        }
//        System.out.println(trees);
        System.out.println(mx);
    }

    private static void populateTop2Height(Vertex tree) {
        if (!tree.ladder.isEmpty()) {
            return;
        }
        tree.maxSoFar = tree.score;
        if (!tree.children.isEmpty()) {
            for (Vertex t : tree.children) {
                populateTop2Height(t);
                tree.maxSoFar = Math.max(tree.maxSoFar, t.maxSoFar);
                for (List<Vertex> v : t.ladder) {
                    tree.maxSoFar = Math.max(tree.maxSoFar, maxInLadder(tree.ladder, v) + tree.score);
                }
                for (List<Vertex> v : t.ladder) {
                    tree.ladder.add(new ArrayList<>(v));
                }
            }
//            System.out.println(tree.word);

            tree.ladder = reduce(tree.ladder);
            for (List<Vertex> l : tree.ladder) {
                l.add(tree);
            }

//            for (List<Vertex> l : tree.ladder) {
//                System.out.println(l);
//            }
        } else {
            tree.ladder.add(new ArrayList<>(Arrays.asList(tree)));
        }
//        System.out.println(tree.word);
//        for (List<Vertex> l : tree.ladder) {
//            System.out.println(l);
//        }
    }

    private static List<List<Vertex>> reduce(List<List<Vertex>> ladder) {
        int sz = ladder.size();
        int mxsz = 0;
        for (List<Vertex> lad : ladder) {
            mxsz = Math.max(mxsz, lad.size());
        }
        Map<Integer, Set<String>> choosen = new HashMap<>();
        int sc[] = new int[sz];
        for (int h = 0; h < mxsz; ++h) {
            // first max
            for (int i = 0; i < sz; ++i) {
                if (h < ladder.get(i).size()) {
                    sc[i] += ladder.get(i).get(ladder.get(i).size() - h - 1).score;
                } else {
                    sc[i] = 0;
                }
            }

            Map<Integer, Integer> scores = new HashMap<>();
            for (int i = 0; i < sz; ++i) {
                if (sc[i] > 0) {
                    scores.put(i, sc[i]);
                }
            }
            List<Entry<Integer, Integer>> sorted = new ArrayList<>(scores.entrySet());
            Collections.sort(sorted, new Comparator<Entry<Integer, Integer>>() {

                @Override
                public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
                    return o2.getValue() - o1.getValue();
                }
            });
            // first
            int mxi = sorted.get(0).getKey();
            // System.out.println(mxi + ":" + sorted);
            Set<String> taken = new HashSet<>();
            choosen.put(mxi, taken);

            for (int k = 0; k <= h; ++k) {
                taken.add(ladder.get(mxi).get(ladder.get(mxi).size() - k - 1).word);
            }

            // second max
            boolean isc = false;
            for (int i = 1; i < sorted.size(); ++i) {
                int x = sorted.get(i).getKey();
                for (int k = 0; k <= h; ++k) {
                    isc = taken.contains(ladder.get(x).get(ladder.get(x).size() - k - 1).word);
                    if (isc) {
                        break;
                    }
                }
                if (!isc) {
                    choosen.put(x, new HashSet<String>());
                    break;
                } else {
                    choosen.put(x, new HashSet<String>());
                }
            }
        }
        List<List<Vertex>> newLadder = new ArrayList<>();
        for (Integer i : choosen.keySet()) {
            newLadder.add(ladder.get(i));
        }
        return newLadder;
    }

    private static int maxInLadder(List<List<Vertex>> ladder, List<Vertex> v) {
        int sz = ladder.size();
        int mx = 0;
        for (int j = 0; j < sz; ++j) {
            Set<String> taken = new HashSet<>();
            int isz = v.size();
            int jsz = ladder.get(j).size();
            int len = Math.min(isz, jsz);
            int sum = 0;
            for (int k = 0; k < len; ++k) {
                if (!taken.contains(v.get(isz - k - 1).word)) {
                    taken.add(v.get(isz - k - 1).word);
                } else {
                    break;
                }
                if (!taken.contains(ladder.get(j).get(jsz - k - 1).word)) {
                    taken.add(ladder.get(j).get(jsz - k - 1).word);
                } else {
                    break;
                }
                sum += v.get(isz - k - 1).score + ladder.get(j).get(jsz - k - 1).score;
            }
            mx = Math.max(mx, sum);

        }
        return mx;
    }

    private static Vertex dfsLadder(char[] word) {
        String s = new String(word);
        Vertex tree = new Vertex(s);
        visited.put(s, tree);
        for (int i = 0, _n = word.length; i < _n; ++i) {
            char t = word[i];
            int point = index[t - 'A'];
            for (int p = point - 1; p >= 0; --p) {
                for (char c : replacements.get(p)) {
                    word[i] = c;
                    String w = new String(word);
                    if (visited.get(w) != null) {
                        tree.children.add(visited.get(w));
                    } else if (dictionary.contains(w)) {
                        tree.children.add(dfsLadder(word));
                    }
                }
            }
            word[i] = t;
        }
        return tree;
    }
}
