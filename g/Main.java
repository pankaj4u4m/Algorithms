import java.io.*;
import java.math.*;

class Fraction{
	public BigInteger x = BigInteger.ONE;
	public BigInteger y = BigInteger.ONE;

	public Fraction(){}	
	public Fraction(BigInteger a,  BigInteger b){
		x = a;
		y = b;
	}
	public Fraction add(Fraction fac){
		Fraction f = new Fraction();
		BigInteger a = x.multiply(fac.y);
		BigInteger b = fac.x.multiply(y);
		BigInteger c = a.add(b);
		BigInteger d = y.multiply(fac.y);
		BigInteger g = c.gcd(d);
		c = c.divide(g);
		d = d.divide(g);
		f.x = c;
		f.y = d;
		return f;
	}
	public Fraction subtract(Fraction fac){
		Fraction f = new Fraction();
		BigInteger a = x.multiply(fac.y);
		BigInteger b = fac.x.multiply(y);
		BigInteger c = a.subtract(b);
		BigInteger d = y.multiply(fac.y);
	//	BigInteger g = c.gcd(d);
	//	c = c.divide(g);
	//	d = d.divide(g);
		f.x = c;
		f.y = d;
		return f;
	}
	public Fraction multiply(Fraction fac){
		Fraction f = new Fraction();
		f.x = x.multiply(fac.x);
		f.y = y.multiply(fac.y);
		BigInteger g = f.x.gcd(f.y);
		f.x = f.x.divide(g);
		f.y = f.y.divide(g);
		return f;
	}
	public Fraction divide(Fraction fac){
		Fraction f = new Fraction();
		f.x = x.multiply(fac.y);
		f.y = y.multiply(fac.x);
		BigInteger g = f.x.gcd(f.y);
		f.x = f.x.divide(g);
		f.y = f.y.divide(g);
		return f;
	}
}
class SUP{
	BigInteger Fact[] = new BigInteger[155];
	BigInteger match[] = new BigInteger[155];
	Fraction saved[] = new Fraction[155];
	Fraction single[] = new Fraction[155];
	Fraction mem[] = new Fraction[155];
	boolean acc[] = new boolean[155];
	Fraction ONE = new Fraction();
	public SUP(){
		Fact[0] = BigInteger.ONE;
		Fact[1] = BigInteger.ONE;
		match[0] = BigInteger.ONE;
		match[1] = BigInteger.ONE;
		saved[0] = new Fraction(BigInteger.ZERO, BigInteger.ONE);
		saved[1] = new Fraction(BigInteger.ZERO, BigInteger.ONE);
		single[0] = new Fraction(BigInteger.ZERO, BigInteger.ONE);
		single[1] = new Fraction(BigInteger.ZERO, BigInteger.ONE);
		for(int i = 2; i< 151; i++){
			Fact[i] = Fact[i-1].multiply(BigInteger.valueOf(i));
			match[i] = Fact[i-2].multiply(BigInteger.valueOf(i*i - 3*i + 3));
			
			
			Fraction a1 = new Fraction(Fact[i].subtract(match[i]), BigInteger.ONE);
			
			saved[i] = saved[i-1].add(single[i-1]);
			mem[i] = saved[i].add(new Fraction(match[i].add(BigInteger.ONE), BigInteger.ONE));
			mem[i]= mem[i].divide(a1);
			
			a1 = mem[i].add(ONE).multiply(new Fraction(match[i], BigInteger.ONE));
			saved[i] = saved[i].add(a1);
			single[i] = single[i-1].add(a1);
			
		//	System.out.println(""+mem[i].x+"**"+mem[i].y);
		
		}
	}
	public Fraction solve(int n){return mem[n];}
/*	public Fraction solve(int n){
		if(acc[n])return mem[n];

		Fraction f = new Fraction(BigInteger.ONE, BigInteger.ONE);
		if(n == 0 || n == 1){
			saved[0] = f;
			saved[1] = f;
			return f;
		}
		f.x = BigInteger.valueOf(2);
		if(n == 2){
			saved[2] = f;
			saved[2].x = saved.x.add(BigInteger.ONE)
			return f;
		}
		Fraction a1 = new Fraction(Fact[n],  BigInteger.ONE).subtract(match[n]);
		Fraction a = match[n];
		for(int i = n-1; i>=2; i--){
			Fraction s = solve(i);
			s = s.add(ONE);
			s.x = s.x.multiply(BigInteger.valueOf(n-i+1));
			a = a.add(t);
		}
		return mem[n];
	}
*/
}
class Main{
	public static void main(String agrv[])throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		SUP s = new SUP();
	//	System.out.println("out");
		while(T-->0){
			int n = Integer.parseInt(br.readLine());
			Fraction t = s.solve(n);
			if(t.y.compareTo(BigInteger.ONE) == 0){
				System.out.println(""+t.x);
			}
			else{
				System.out.println(""+t.x+"/"+t.y);
			}
		}
	}
}

