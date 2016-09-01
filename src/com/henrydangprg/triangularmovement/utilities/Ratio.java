package com.henrydangprg.triangularmovement.utilities;

public class Ratio {
	double[] num;
	double largest;
	
	/**
	 * Creates a ratio based off a list of numbers.
	 * 
	 * @param num  the list of numbers in the ratio.
	 */
	public void add(double... num) {
		largest = 0;
		this.num = num;
	}
	
	/**
	 * Multiplies a ratio by a scale factor.
	 * 
	 * <p>Calculates a scale factor from the largest number,
	 * and multiplies every number in the ratio by the
	 * scale factor.
	 * 
	 * @param scaleFactor  the scale factor to be multiplied within the ratio.
	 */
	public void amplifyRatio(double scaleFactor) {
		for (double number : this.num) {
			if (Math.abs(number) > largest) largest = Math.abs(number);
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