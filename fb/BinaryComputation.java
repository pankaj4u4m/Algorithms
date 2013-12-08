package fb;

public class BinaryComputation {

	public static int modulo(int a, int b, int c){
		long x = 1, y = a;
		while(b > 0){
			if((b&1) == 1){
				y = (x * y) % c;
			}
			y = (y * y) % c;
			b >>=1;
		}
		return (int)y%c;
	}
}
