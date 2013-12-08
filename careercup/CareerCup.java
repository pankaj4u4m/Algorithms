import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Queue;

import practice.algo.Pair;

public class CareerCup {

    /**
     * largest interval in the list having all the elements in the range in the list.
     */
    static Entry<Integer, Integer> getRange(int[] a) {
        HashMap<Integer, Integer> range = new HashMap<>();
        for (int x : a) {
            range.put(x, x);
        }
        for (int x : a) {
            int r = range.get(x);
            Integer v = dfsRange(r + 1, range);
            if (v != null) {
                range.put(x, v);
            }
        }
        Entry<Integer, Integer> res = null;
        int mx = 0;
        for (Entry<Integer, Integer> entry : range.entrySet()) {
            if (mx < entry.getValue() - entry.getKey()) {
                res = entry;
                mx = entry.getValue() - entry.getKey();
            }
        }
        return res;
    }

    private static Integer dfsRange(int i, HashMap<Integer, Integer> range) {
        if (range.get(i) != null) {
            Integer v = dfsRange(range.get(i) + 1, range);
            if (v != null) {
                range.put(i, v);
            }
        }
        return range.get(i);
    }

    static void testGetRange() {
        System.out.println(getRange(new int[] { 1, 7, 4, 6, 3, 10, 2 }));
        System.out.println(getRange(new int[] { 1, 3, 5, 7, 4, 6, 10 }));
        System.out.println(getRange(new int[] { 2, 10, 3, 12, 5, 4, 11, 8, 7, 6, 15 }));

    }

    class Node implements Comparable<Node> {
        int data;
        ArrayList<Node> neighbours;

        Node cloneGraph() {
            HashMap<Node, Node> visited = new HashMap<>();
            Queue<Pair<Node, Node>> queue = new LinkedList<>();

            Node newTop = new Node();
            queue.add(new Pair<>(this, newTop));

            while (!queue.isEmpty()) {
                Pair<Node, Node> top = queue.poll();
                Node root = top.getFirst();
                Node cloned = top.getSecond();
                cloned.data = root.data;
                if (root.neighbours != null) {
                    cloned.neighbours = new ArrayList<>();
                    for (Node node : root.neighbours) {
                        if (visited.get(node) == null) {
                            Node newNode = new Node();
                            queue.add(new Pair<>(this, newNode));

                            cloned.neighbours.add(newNode);
                            visited.put(node, newNode);
                        } else {
                            cloned.neighbours.add(visited.get(node));
                        }
                    }
                }
            }
            return newTop;
        }

        @Override
        public int compareTo(Node o) {
            // TODO Auto-generated method stub
            return 0;
        }
    }

    static void inplaceNegativePositiveSort(int[] a) {
        int n = a.length;

    }

    /**
     * triplets A[i-1]>=A[i]<=A[n+1]
     */
    static int getTriplet(int[] a) {
        int l = a.length;
        if (l < 3) {
            return -1;
        }
        int start = 0;
        int end = l - 1;
        while (end >= start) {
            //
            int mid = (start) + (end - start) / 2;
            if (mid == 0) {
                start = mid + 1;
            } else if (mid == l - 1) {
                end = mid - 1;
            } else if (a[mid - 1] >= a[mid] && a[mid] <= a[mid + 1]) { // mid shortest
                return mid;
            } else if (a[mid - 1] <= a[mid] && a[mid] >= a[mid + 1]) { // mid largest
                start = mid + 1;
            } else if (a[mid - 1] <= a[mid] && a[mid] <= a[mid + 1]) { // i-1 < i
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }

    static void testGetTriplet() {
        System.out.println("5=" + getTriplet(new int[] { 9, 8, 5, 4, 3, 2, 6, 7 }));
        System.out.println("any=" + getTriplet(new int[] { 1, 1, 1, 1, 1, 1, 1 }));
        System.out.println("1=" + getTriplet(new int[] { 1, 1, 2, 3, 4, 5, 6 }));
        System.out.println("1=" + getTriplet(new int[] { 1, 0, 1 }));
    }

    class LinkedListNode<T> {
        private final T data;
        private LinkedListNode<T> next;

        public LinkedListNode(T d) {
            this.data = d;
            setNext(null);
        }

        public T getData() {
            return data;
        }

        public LinkedListNode<T> getNext() {
            return next;
        }

        public void setNext(LinkedListNode<T> next) {
            this.next = next;
        }

    }

    LinkedListNode<Integer> reverseLinkedList(LinkedListNode<Integer> list) {
        if (list == null) {
            return null;
        }
        LinkedListNode<Integer> prev = null;
        LinkedListNode<Integer> current = list;
        while (current != null) {
            LinkedListNode<Integer> next = current.getNext();
            current.setNext(prev);
            prev = current;
            current = next;
        }
        return prev;
    }

    void testReverseLiunkedList() {
        LinkedListNode<Integer> head = null;
        LinkedListNode<Integer> tail = null;
        for (int i = 0; i < 10; ++i) {
            if (head == null) {
                head = tail = new LinkedListNode<Integer>(i);
            } else {
                tail.setNext(new LinkedListNode<Integer>(i));
                tail = tail.getNext();
            }
        }
        LinkedListNode<Integer> iter = head;
        while (iter != null) {
            System.out.print(iter.getData() + "->");
            iter = iter.getNext();
        }
        System.out.println();
        head = reverseLinkedList(head);

        iter = head;
        while (iter != null) {
            System.out.print(iter.getData() + "->");
            iter = iter.getNext();
        }
        System.out.println();
    }

    double sqrt(double x) {
        if (x < 0) {
            return -1;
        }
        double start = 0;
        double end = x;
        if (x < 1) {
            end = 1;
        }
        while (start < end) {
            double mid = start + (end - start) / 2;
            if (Math.abs(mid * mid - x) < 0.0000000000001 * x) {
                return mid;
            } else if (mid * mid < x) {
                start = mid;
            } else {
                end = mid;
            }
        }
        return end;
    }

    void testSqrt() {
        System.out.println("10000=" + sqrt(10000));
        System.out.println("12345678=" + sqrt(1245678));
        System.out.println("25=" + sqrt(25));
        System.out.println(".01=" + sqrt(0.01));
    }

    int upperBound(int[] a, int v) {
        int start = 0;
        int end = a.length;
        while (start <= end) {
            int mid = (start) + (end - start) / 2;
            if (a[mid] > v) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return end;
    }

    int lowerBound(int[] a, int v) {
        int start = 0;
        int end = a.length;
        while (start <= end) {
            int mid = (start) + (end - start) / 2;
            if (a[mid] < v) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return start;
    }

    void testUpperLowerBound() {
        int a[] = { 1, 1, 1, 2, 2, 3, 3, 3, 4, 4, 5, 6, 7, 8, 8, 8, 8, 9 };
        System.out.println("2=" + (upperBound(a, 4) - lowerBound(a, 4) + 1));
        System.out.println("1=" + (upperBound(a, 5) - lowerBound(a, 5) + 1));
        System.out.println("4=" + (upperBound(a, 8) - lowerBound(a, 8) + 1));
    }

    void printSpiral(int[][] a) {
        int n = a.length;
        int m = a[0].length;
//        if (n == 1 && m == 1) {
//            System.out.println(a[0][0]);
//            System.out.println();
//            return;
//        }
        int mn = Math.min(m, n);
        int j, i;
        for (int layer = 0; layer < (mn + 1) / 2; ++layer) {
            i = layer;
            j = layer;
            for (; j < m - layer; ++j) { // top
                System.out.println(a[i][j]);
            }
            --j;
            i++;
            for (; i < n - layer; ++i) { // right
                System.out.println(a[i][j]);
            }
            if ((mn & 1) != 0 && layer == (mn + 1) / 2 - 1) {
                continue;
            }
            --i;
            j--;
            for (; j >= layer; --j) { // bottom
                System.out.println(a[i][j]);
            }
            ++j;
            i--;
            for (; i >= layer + 1; --i) { // left
                System.out.println(a[i][j]);
            }
        }
        System.out.println();
    }

    void testPrintSpiral() {
        printSpiral(new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } });
        printSpiral(new int[][] { { 1 } });
        printSpiral(new int[][] { { 1 }, { 2 }, { 3 } });
        printSpiral(new int[][] { { 1, 2, 3, 4 } });
        printSpiral(new int[][] { { 1, 2 }, { 3, 4 } });
    }

    int nByxRepeatedNumber(int a[], int x) {
        int l = a.length;
        Pair<Integer, Integer> slot[] = new Pair[x - 1];
        HashMap<Integer, Integer> slotvalue = new HashMap<>();
        Queue<Integer> emptyslots = new LinkedList<>();
        for (int j = 0; j < x - 1; ++j) {
            emptyslots.add(j);
        }
        for (int i = 1; i < l; ++i) {
            if (slotvalue.get(a[i]) != null) {
                slot[slotvalue.get(a[i])].setFirst(slot[slotvalue.get(a[i])].getFirst() + 1);

            } else if (!emptyslots.isEmpty()) {
                Integer poll = emptyslots.poll();
                slot[poll] = new Pair(1, a[i]);
                slotvalue.put(a[i], poll);
            } else {
                for (int j = 0; j < x - 1; ++j) {
                    slot[j].setFirst(slot[j].getFirst() - 1);
                    if (slot[j].getFirst() == 0) {
                        emptyslots.add(j);
                        slotvalue.remove(slot[j].getSecond());
                    }
                }
            }
        }
        for (int j = 0; j < x - 1; ++j) {
            if (slot[j] != null && slot[j].getFirst() != 0) {
                return slot[j].getSecond();
            }
        }
        return -1;
    }

    void testNBy10RepeatedNumber() {
        System.out.println(nByxRepeatedNumber(new int[] { 2, 5, 4, 5, 6, 1, 7, 8, 5, 2, 3, 1, 4, 5 }, 10));
        System.out.println(nByxRepeatedNumber(new int[] { 2, 5, 2, 2, 6, 1, 2, 8, 2, 2, 3, 2, 2, 5 }, 2));
    }

    int getLargestSubsequenceProduct(int a[]) {
        int n = a.length;
        int currentPos = 1;
        int currentNeg = 1;
        int maxPos = Integer.MIN_VALUE;
        for (int i = 0; i < n; ++i) {
            if (a[i] == 0) {
                currentNeg = currentPos = 1;
            }
            if (a[i] < 0) {
                int t = currentPos;
                currentPos = currentNeg * a[i];
                currentNeg = t * a[i];
            } else {
                currentPos *= a[i];
            }
            if (currentPos > maxPos) {
                maxPos = currentPos;
            }
            if (currentPos < 1) {
                currentPos = 1;
            }
        }
        return maxPos;
    }

    void testGetLargestSubsequenceProduct() {
        System.out.println(getLargestSubsequenceProduct(new int[] { -1 }));
        System.out.println(getLargestSubsequenceProduct(new int[] { -1, -1 }));
        System.out.println(getLargestSubsequenceProduct(new int[] { -1, 3 }));
        System.out.println(getLargestSubsequenceProduct(new int[] { -1, 3, -4 }));
    }

    int aSideDiceSum(int s, int n, int a, Integer[][] mem) {
        if (n == 0 && s == 0) {
            return 1;
        }
        if (n < 1) {
            return 0;
        }
        if (s < 1) {
            return 0;
        }
        if (mem[s][n] != null) {
            return mem[s][n];
        }

        int r = 0;
        for (int i = 1; i <= a; ++i) {
            r += aSideDiceSum(s - i, n - 1, a, mem);
        }
        mem[s][n] = r;
        return mem[s][n];
    }

    void testASideDiceSum() {
        Integer mem[][] = new Integer[20][10];
        System.out.println(aSideDiceSum(18, 8, 6, mem));
    }

    int oneInThreeRepeat(int a[]) {
        int three = 0;
        int two = 0;
        int one = 0;
        for (int i = 0; i < a.length; i++) {
            three = (two ^ one) & a[i];
            two = one & a[i];
            one ^= a[i];
        }
        return three ^ two ^ one;
    }

    void testOneInThreeRepeat() {
        System.out.println("5=" + oneInThreeRepeat(new int[] { 2, 1, 4, 5, 1, 4, 2, 2, 4, 1 }));
        System.out.println("5=" + oneInThreeRepeat(new int[] { 1, 4, 5, 1, 4, 4, 1 }));
        System.out.println("4=" + oneInThreeRepeat(new int[] { 1, 5, 5, 1, 4, 5, 1, 7, 3, 3, 3, 7, 7 }));
    }

    void printValidParanthesis(int i, int left, int right, char[] str, int n) {
        if (left == right && i == n) {
            System.out.println(str);
        } else if (right == n / 2) {
            str[i] = ')';
            printValidParanthesis(i + 1, left + 1, right, str, n);
        } else if (left == right) {
            str[i] = '(';
            printValidParanthesis(i + 1, left, right + 1, str, n);
        } else if (left < right) {
            str[i] = ')';
            printValidParanthesis(i + 1, left + 1, right, str, n);
            str[i] = '(';
            printValidParanthesis(i + 1, left, right + 1, str, n);
        }
    }

    void testPrintValidParanthesis() {
        printValidParanthesis(0, 0, 0, new char[10], 10);
    }

    boolean matchPattern(String text, String pattern, int i, int p) {
        if (p == pattern.length() && i == text.length()) {
            return true;
        } else if (p >= pattern.length()) {
            return false;
        } else if (i == text.length()) {
            return false;
        } else if (pattern.charAt(p) == '*') {
            return matchPattern(text, pattern, i + 1, p) || matchPattern(text, pattern, i + 1, p + 1)
                    || matchPattern(text, pattern, i, p + 1);
        } else if (pattern.charAt(p) == text.charAt(i)) {
            return matchPattern(text, pattern, i + 1, p + 1);
        } else {
            return false;
        }
    }

    void allZeroAtEnd(int[] array) {
        int exchange = 0;
        for (int i = 0; i < array.length; ++i) {
            if (array[i] != 0) {
                swap(array, i, exchange);
                ++exchange;
            }
        }
        System.out.println(Arrays.toString(array));
    }

    private void swap(int[] array, int i, int exchange) {
        int t = array[i];
        array[i] = array[exchange];
        array[exchange] = t;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // testGetRange();
        // testGetTriplet();
        // new CareerCup().testReverseLiunkedList();
        // new CareerCup().testSqrt();
        // new CareerCup().testUpperLowerBound();
        // new CareerCup().testPrintSpiral();
        // new CareerCup().testNBy10RepeatedNumber();
        // new CareerCup().testGetLargestSubsequenceProduct();
        // new CareerCup().testASideDiceSum();
        // new CareerCup().testOneInThreeRepeat();
        // new CareerCup().testPrintValidParanthesis();
        new CareerCup().allZeroAtEnd(new int[] { 1, 2, 0, 4, 0, 0, 8 });
    }

}
