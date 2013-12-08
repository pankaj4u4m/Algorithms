package illuminati;

import java.util.Arrays;

public class Palindrome {

    private char[] array;
    private int n;
    private int half;

    public Palindrome(char[] array) {
        this.array = array;
        n = array.length;
        half = (n - 1) / 2;
    }

    public String nextPalindrome() {
        boolean sizeExceed = false;
        if (splitCompare(array, n, half) <= 0) {
            sizeExceed = incrementLeft();
        }
        if (sizeExceed) {
            char[] newNumber = Arrays.copyOf(array, n + 1);
            newNumber[0] = '1';
            newNumber[half + 1] = '0';
            n += 1;
            half = (n - 1) / 2;
            array = newNumber;
        }
        replaceRight();
        return new String(array);
    }

    private boolean incrementLeft() {
        int h = half;
        while (h >= 0) {
            if (array[h] == '9') {
                array[h] = '0';
            } else {
                array[h] += 1;
                break;
            }
            --h;
        }
        if (h < 0) {
            return true;
        }
        return false;
    }

    private void replaceRight() {
        int h = half;
        while (h >= 0) {
            array[n - h - 1] = array[h];
            --h;
        }
    }

    private int splitCompare(char[] number, int n, int half) {
        int h = half;
        while (h >= 0) {
            if (number[h] > number[n - h - 1]) {
                return 1;
            } else if (number[h] < number[n - h - 1]) {
                return -1;
            }
            --h;
        }
        return 0;
    }

    public int makePalindromeCost() {
        Integer mem[][] = new Integer[n][n];
        return calculateMinCost(0, n - 1, mem);
    }

    private int calculateMinCost(int i, int j, Integer[][] mem) {
        if (i >= j) {
            return 0;
        } else if (mem[i][j] != null) {
            return mem[i][j];
        } else {
            int r = Math.min(Math.min(calculateMinCost(i, j - 1, mem) + 1, // delete right
                    calculateMinCost(i + 1, j, mem) + 1), // delete left
                    calculateMinCost(i + 1, j - 1, mem) + (array[i] != array[j] ? 1 : 0)); // consider it as palin char;
            mem[i][j] = r;
            return r;
        }
    }
}
