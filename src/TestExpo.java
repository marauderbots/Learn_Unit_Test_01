/**
 * Example class to provide a simple testable function
 * 
 * @author Jason Kusnier
 *
 */
public class TestExpo {

	/**
	 * A basic main method to show that our method works
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		TestExpo testExpo = new TestExpo();
		for (double i = -10; i <= 10; i += .01) {
			System.out.println(i);
			System.out.println(testExpo.getExpoRate(i));
		}

	}

	/**
	 * Given input from -1 to 1 this function returns a cubed rate
	 * 
	 * @param input
	 * @return
	 */
	public double getExpoRate(double input) {
		// Prevent out of bounds input data
		if (input > 1) {
			input = 1;
		} else if (input < -1) {
			input = -1;
		}

		return Math.pow(input, 3);
	}
}
