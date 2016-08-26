package com.henrydangprg.triangularmovement.utilities;

public class Ratio {
	double[] num;
	double sum = 0;
	double largest = 0;
	
	public void add(double... num) {
      	sum = 0;
      	largest = 0;
		this.num = num;
	}
	
	public void calcRatio() {
		for (double number : this.num) {
			sum += Math.abs(number);
		}
		
		for (int count = 0; count < num.length; count++) {
			num[count] = num[count] / sum;
		}
	}
	
	public void amplifyRatio(double maxValue) {
		for (double number : this.num) {
			if (Math.abs(number) > largest) largest = Math.abs(number);
		}
		
		double amplifyValue = maxValue / largest;
		
		for (int count = 0; count < num.length; count++) {
			num[count] = num[count] * amplifyValue;
		}
	}
	
	public double[] getRatio() {
		return num;
	}
}