package illuminati;


public class Sequence {
    private static final int MOD = 100000007;
    private final int A0;
    private final int A1;
    private final int a;
    private final int b;

    public Sequence(int p, int q, int a0) {
        this.a = p;
        this.b = q;
        this.A0 = a0;
        this.A1 = A0 + A0;
    }

    public Sequence(int a, int b, int a0, int a1) {
        this.a = a;
        this.b = b;
        this.A0 = a0;
        this.A1 = a1;
    }

    long nthTerm1(int n) { // No need to remember past, number of computation is very less
        if (n == 0) {
            return A0;
        } else {
            return nthTerm1(n / a) + nthTerm1(n / b);
        }
    }

    long nthTerm2(int n) {
        long f[][] = new long[][] { { 0, 1 }, { b, a } };
        long r[][] = powmod(f, n);
        return (r[0][0] * A0 + r[0][1] * A1) % MOD;
    }

    private long[][] powmod(long[][] f, int n) {
        long r[][] = new long[][] { { 1, 0 }, { 0, 1 } };
        while (n > 0) {
            if ((n & 1) == 1) {
                multiply(r, f);
            }
            multiply(f, f);
            n >>= 1;
        }
        return r;
    }

    private void multiply(long[][] r, long[][] f) {
        long x = r[0][0] * f[0][0] + r[0][1] * f[1][0];
        long y = r[0][0] * f[0][1] + r[0][1] * f[1][1];
        long z = r[1][0] * f[0][0] + r[1][1] * f[1][0];
        long s = r[1][0] * f[0][1] + r[1][1] * f[1][1];
        r[0][0] = x % MOD;
        r[0][1] = y % MOD;
        r[1][0] = z % MOD;
        r[1][1] = s % MOD;
    }
}
