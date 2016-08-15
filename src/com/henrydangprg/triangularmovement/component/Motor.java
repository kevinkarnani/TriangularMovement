package com.henrydangprg.triangularmovement.component;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Motor {
	
	Circle motor = new Circle();
	
	Coordinate motorCoord;

	private Encoder encoder;
	private final double constant = 100.0/60.0;
	
	public Motor(double[] pos, Encoder encoder){
		motorCoord = new Coordinate(pos[0], pos[1]);
		motor.setRadius(10);
		motor.setCenterX(motorCoord.getX());
		motor.setCenterY(motorCoord.getY());
		motor.setFill(Color.GRAY);
		this.encoder = encoder;
	}
	
	public void rotate(MotorValues value){
		encoder.setValue(encoder.getValue() + constant);
	}
	
	public Circle getMotorShape() {
		return motor;
	}
	
	public double[] getMotorPosition() {
		return motorCoord.getXY();
	}
	
	public double getEncoderValue(){
		return encoder.getValue();
	}
}
