package com.henrydangprg.triangularmovement.component;

public class Encoder {
	private double value = 0;
	
	/**
	 * Defines the value of the encoder.
	 * 
	 * @param value  the value of the encoder.
	 */
	public void setValue(double value){
		this.value = value;
	}
	
	/**
	 * Returns the value of the encoder.
	 * 
	 * @return  the value of the encoder as a double.
	 */
	public double getValue(){
		return this.value;
	}
}