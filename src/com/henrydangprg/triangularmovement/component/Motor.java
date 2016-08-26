package com.henrydangprg.triangularmovement.component;

import com.henrydangprg.triangularmovement.utilities.Coordinate;
import com.henrydangprg.triangularmovement.utilities.MathUtil;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Motor {
	
	double speed;
	
	Circle motor = new Circle();
	
	Coordinate motorCoord;

	private Encoder encoder;
	private final double MOTOR_SPEED = 2;
	
	public Motor() {
		encoder = new Encoder();
	}
	
	public void setPosition(Coordinate motorCoord) {
		this.motorCoord = motorCoord;
		motor.setRadius(10);
		motor.setCenterX(motorCoord.getX());
		motor.setCenterY(motorCoord.getY());
		motor.setFill(Color.GRAY);
	}
	
	public void set(double speed) {
		this.speed = MathUtil.range(speed, -1.0, 1.0);
	}
	
	public void rotate(){
		encoder.setValue(this.getEncoderValue() + speed*MOTOR_SPEED);
	}
	
	public double getSpeed() {
		return speed;
	}
	
	public double getMotorSpeed() {
		return MOTOR_SPEED;
	}
	
	public Circle getMotorShape() {
		return motor;
	}
	
	public Encoder getEncoder() {
		return encoder;
	}
	
	public Coordinate getCoordinate() {
		return motorCoord;
	}
	
	public double getEncoderValue(){
		return encoder.getValue();
	}
}