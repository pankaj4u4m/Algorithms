public class Happy {

	public native void printText();

	static {
		System.loadLibrary("happy"); /* Note lowercase of classname! */
	}

	public static void main(final String[] args) {
		final Happy happy = new Happy();
		happy.printText();
	}

}
