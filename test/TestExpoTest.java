import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Example Unit Test for TestExpo
 * 
 * @author Jason Kusnier
 *
 */
class TestExpoTest {
	@BeforeAll
	/**
	 * This method will run once at the beginning
	 * 
	 * @throws Exception
	 */
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	/**
	 * This method will run once at the end
	 * 
	 * @throws Exception
	 */
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	/**
	 * This method will run once before each @Test method
	 * 
	 * @throws Exception
	 */
	void setUp() throws Exception {
	}

	@AfterEach
	/**
	 * This method will run once after each @Test method
	 * 
	 * @throws Exception
	 */
	void tearDown() throws Exception {
	}

	@Test
	/**
	 * Test from -10 to 10 in 0.01 increments
	 */
	void testOuterBoundaries() {
		TestExpo testExpo = new TestExpo();
		for (double i = -10; i <= 10; i += .01) {
			double expoRate = testExpo.getExpoRate(i);
			assertTrue(expoRate >= -1 && expoRate <= 1);
		}
	}

	@Test
	/**
	 * Test for expected value of 0
	 */
	void testZero() {
		TestExpo testExpo = new TestExpo();
		assertTrue(testExpo.getExpoRate(0) == 0);
	}

	@Test
	/**
	 * Test for expected value of 1
	 */
	void testOne() {
		TestExpo testExpo = new TestExpo();
		assertTrue(testExpo.getExpoRate(1) == 1);
	}

	@Test
	/**
	 * Test for expected value of -1
	 */
	void testNegativeOne() {
		TestExpo testExpo = new TestExpo();
		assertTrue(testExpo.getExpoRate(-1) == -1);
	}

	@Test
	/**
	 * Testing the cubed output This test is more to show how floating point
	 * precision can be fun
	 */
	void testCubed() {
		TestExpo testExpo = new TestExpo();
		double cubedRate = testExpo.getExpoRate(0.1);

		// This will almost never work
		// since 0.1^3 on a computer is something like 0.0010000000000000002
		// assertTrue(cubedRate == 0.001);

		// This will usually work if we do the calculation
		assertTrue(Double.compare(cubedRate, Math.pow(0.1, 3)) == 0);

		// Floating point math is fun, sometimes you may have to put in a valid
		// threshold
		assertTrue(cubedRate >= 0.0009 && cubedRate <= 0.0019);

		// This way is a bit cleaner and reusable
		assertTrue(equalWithPrecision(cubedRate, 0.001, 3));

		// Yet another way using 64bit epsilon
		// The preferred way
		assertTrue(equalWith64BitPrecision(cubedRate, 0.001));
	}

	/**
	 * Test double equality based on a giving floating point precision
	 * 
	 * @param a
	 * @param b
	 * @param precision
	 * @return
	 */
	private boolean equalWithPrecision(double a, double b, int precision) {
		return Math.abs(a - b) <= Math.pow(10, -precision);
	}

	/**
	 * Test double equality with 64bit epsilon
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	private boolean equalWith64BitPrecision(double a, double b) {
		return Math.abs(a / b - 1) < 5.96e-08;
	}
}
