package com.henrydangprg.triangularmovement.component;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ghost {

	private Motor motorLeft;
	private Motor motorTop;
	private Motor motorRight;

	private Circle ghostRepresentation;
	
	private Coordinate coord;
	
	public Ghost(Motor motorLeft, Motor motorTop, Motor motorRight){
		this.motorLeft = motorLeft;
		this.motorTop = motorTop;
		this.motorRight = motorRight;
		coord = new Coordinate();
		ghostRepresentation = new Circle(); 
		ghostRepresentation.setRadius(10);
		ghostRepresentation.setFill(Color.CHOCOLATE);
	}
	
	public Coordinate getPosition(){
		return coord;
	}
	
	public Circle getGhost() {
		return ghostRepresentation;
	}

	public void setPosition() {
	}
	
	public void resetToCenter() {
		coord.setX(motorRight.getMotorPosition().getX());
		coord.setY(motorRight.getMotorPosition().getY());
		ghostRepresentation.setCenterX(coord.getX());
		ghostRepresentation.setCenterY(coord.getY());
	}

	public Coordinate getCoordinate(){
		return coord;
	}
}
