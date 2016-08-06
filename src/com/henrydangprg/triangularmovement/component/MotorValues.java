package com.henrydangprg.triangularmovement.component;

public enum MotorValues {
	FORWARD(1.0), BACKWARDS(-1.0);
	
	private double value;

	MotorValues(double value){
		this.value = value;
	}
	
	double getValue() {
		return value;
	}
}
