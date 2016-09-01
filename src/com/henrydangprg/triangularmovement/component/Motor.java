package com.henrydangprg.triangularmovement.component;

import com.henrydangprg.triangularmovement.utilities.Coordinate;
import com.henrydangprg.triangularmovement.utilities.MathUtil;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Motor {
	Circle motor = new Circle();
	
	Coordinate motorCoord;

	private Encoder encoder;
	private final double MOTOR_SPEED = 1;
	private double speed;
	
	/**
	 * Creates an Motor with an Encoder.
	 */
	public Motor() {
		encoder = new Encoder();
	}
	
	/**
	 * Defines the position of the motor.
	 * 
	 * @param motorCoord  the coordinate where the motor rests.
	 */
	public void setPosition(Coordinate motorCoord) {
		this.motorCoord = motorCoord;
		motor.setRadius(10);
		motor.setCenterX(motorCoord.getX());
		motor.setCenterY(motorCoord.getY());
		motor.setFill(Color.GRAY);
	}
	
	/**
	 * Sets the speed of the motor ranging from -1 to 1.
	 * 
	 * <p>If the values goes above 1 or below -1, it will
	 * set it to 1 or -1, respectively.
	 * 
	 * @param speed  the speed of the motor.
	 */
	public void set(double speed) {
		this.speed = MathUtil.range(speed, -1.0, 1.0);
	}
	
	/**
	 * Rotates the motor based off the speed.
	 * 
	 * <p><b>This method should always be called to have
	 * the most accurate simulation.</b>
	 */
	public void rotate(){
		encoder.setValue(this.getEncoderValue() + speed*MOTOR_SPEED);
	}
	
	/**
	 * Returns the speed of the motor.
	 * 
	 * @return  the speed of the motor as a double.
	 */
	public double getSpeed() {
		return speed;
	}
	
	/**
	 * Returns the encoder value.
	 * 
	 * <p>The encoder value is how much wire the motor has released.
	 * 
	 * @return  the encoder value as a double.
	 */
	public double getEncoderValue(){
		return encoder.getValue();
	}
	
	/**
	 * Returns the visual motor shape for JavaFX.
	 * 
	 * @return  the motor Circle object.
	 */
	public Circle getMotorShape() {
		return motor;
	}

	/**
	 * Returns the coordinate of the motor.
	 * 
	 * @return the motor Coordinate.
	 */
	public Coordinate getCoordinate() {
		return motorCoord;
	}
	
	/**
	 * Returns the encoder object itself of the motor.
	 * 
	 * @return the Encoder object.
	 */
	public Encoder getEncoder() {
		return encoder;
	}
}