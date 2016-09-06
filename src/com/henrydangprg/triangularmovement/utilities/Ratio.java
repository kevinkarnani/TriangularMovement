package com.henrydangprg.triangularmovement.utilities;

/**
 * Creates a ratio value with an infinite amount of numbers.
 * 
 * <p>
 * The ratio can be amplified to any set maximum value.
 * 
 * <p>
 * Note: The original numbers are in fact a ratio. They
 * are not reduced to a greatest common factor, as that would 
 * have no effect on the original ratio. They would still be the
 * same ratio, no matter what number is multiplied for all of them.
 */
public class Ratio {
    double[] num;
    double largest;

    /**
     * Creates a ratio based off a list of numbers.
     * 
     * @param num
     *            the list of numbers in the ratio.
     */
    public void add(double... num) {
	this.largest = 0;
	this.num = num;
    }

    /**
     * Multiplies a ratio by a scale factor.
     * 
     * <p>
     * Calculates a scale factor from the largest number, and multiplies every
     * number in the ratio by the scale factor.
     * 
     * @param scaleFactor
     *            the scale factor to be multiplied within the ratio.
     */
    public void amplifyRatio(double scaleFactor) {
	for (double number : this.num) {
	    if (Math.abs(number) > largest)
		largest = Math.abs(number);
	}

	double amplifyValue = scaleFactor / largest;

	if (largest != 0) {
	    for (int count = 0; count < num.length; count++) {
		num[count] = num[count] * amplifyValue;
	    }
	}
    }

    /**
     * Returns the entire ratio.
     * 
     * @return returns a double[] with the ratio.
     */
    public double[] getRatio() {
	return num;
    }
}