package com.henrydangprg.triangularmovement.component;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Motor {
	
	Circle motor = new Circle();
	
	Coordinate motorCoord;

	private Encoder encoder;
	private final double constant = 100.0/60.0;
	
	public Motor(){
		encoder = new Encoder();
	}
	
	public void setPosition(Coordinate pos) {
		motorCoord = pos;
		motor.setRadius(10);
		motor.setCenterX(motorCoord.getX());
		motor.setCenterY(motorCoord.getY());
		motor.setFill(Color.GRAY);
	}
	
	public void rotate(double speed){
		encoder.setValue(this.getEncoderValue() + speed);
	}
	
	public Circle getMotorShape() {
		return motor;
	}
	
	public Encoder getEncoder() {
		return encoder;
	}
	
	public Coordinate getMotorCoordinate() {
		return motorCoord;
	}
	
	public double getEncoderValue(){
		return encoder.getValue();
	}
}
