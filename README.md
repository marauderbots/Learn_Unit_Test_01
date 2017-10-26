# Learn_Unit_Test_01
Introduction to Unit Testing

Given a linear input, a function was created to return a cubed rate of the input.

[TestExpo.java](../blob/master/src/TestExpo.java)

```
public double getExpoRate(double input) {
	// Prevent out of bounds input data
	if (input > 1) {
		input = 1;
	} else if (input < -1) {
		input = -1;
	}

	return Math.pow(input, 3);
}
```

![alt text](https://dl.dropboxusercontent.com/s/6zawy8hirxq3rb8/cubed_expo_rate.png "Linear vs Cubed")

[TestExpoTest.java](../blob/master/test/TestExpoTest.java)

## Boundary Test
Starting well below the threshold and continuing in 0.01 increments until past the upper threshold.
Verify that the output never falls out of the acceptable range.

```
void testOuterBoundaries() {
	TestExpo testExpo = new TestExpo();
	for (double i = -10; i <= 10; i += .01) {
		double expoRate = testExpo.getExpoRate(i);
		assertTrue(expoRate >= -1 && expoRate <= 1);
	}
}
```

## Test Expected Values
### Input of 0 should return 0

```
void testZero() {
	TestExpo testExpo = new TestExpo();
	assertTrue(testExpo.getExpoRate(0) == 0);
}
```

### Input of 1 should return 1

```
void testOne() {
	TestExpo testExpo = new TestExpo();
	assertTrue(testExpo.getExpoRate(1) == 1);
}
```

### Input of -1 should return -1

```
void testNegativeOne() {
	TestExpo testExpo = new TestExpo();
	assertTrue(testExpo.getExpoRate(-1) == -1);
}
```

## Test for accurate cubed output
This test shows a few different ways to test `double` equality when floating point precision becomes a factor.

```
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

private boolean equalWithPrecision(double a, double b, int precision) {
	return Math.abs(a - b) <= Math.pow(10, -precision);
}

private boolean equalWith64BitPrecision(double a, double b) {
	return Math.abs(a / b - 1) < 5.96e-08;
}
```
