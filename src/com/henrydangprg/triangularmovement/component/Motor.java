package com.henrydangprg.triangularmovement.component;

public class Motor {

	private Encoder encoder;
	private final double constant = 100.0/60.0;
	
	public Motor(Encoder encoder){
		this.encoder = encoder;
	}
	
	public void rotate(MotorValues value){
		encoder.setValue(encoder.getValue() + constant);
	}
	
	public double getEncoderValue(){
		return encoder.getValue();
	}
}
