package com.henrydangprg.triangularmovement.component;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ghost {

	private Motor motorLeft;
	private Motor motorTop;
	private Motor motorRight;

	private Circle ghostRepresentation;
	
	private Coordinate coord;
	
	private static double TOP_SIZE = 10;
	
	public Ghost(Motor motorLeft, Motor motorTop, Motor motorRight){
		this.motorLeft = motorLeft;
		this.motorTop = motorTop;
		this.motorRight = motorRight;
		coord = new Coordinate();
		ghostRepresentation = new Circle(); 
		ghostRepresentation.setRadius(TOP_SIZE);
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
	
	public void resetToRight() {
		coord.setX(motorRight.getMotorCoordinate().getX());
		coord.setY(motorRight.getMotorCoordinate().getY());
		coord.setZ(motorRight.getMotorCoordinate().getZ());
		ghostRepresentation.setCenterX(coord.getX());
		ghostRepresentation.setCenterY(coord.getY());
		ghostRepresentation.setRadius(TOP_SIZE);
	}

	public Coordinate getCoordinate(){
		return coord;
	}
}
