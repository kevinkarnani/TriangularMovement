package com.henrydangprg.triangularmovement.component;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ghost {

	private Motor motorLeft;
	private Motor motorTop;
	private Motor motorRight;

	private Circle ghostRepresentation;
	
	private Coordinate ghostCoord;
	
	private static double TOP_SIZE = 10;
	
	public Ghost(Motor motorLeft, Motor motorTop, Motor motorRight){
		this.motorLeft = motorLeft;
		this.motorTop = motorTop;
		this.motorRight = motorRight;
		ghostCoord = new Coordinate();
		ghostRepresentation = new Circle(); 
		ghostRepresentation.setRadius(TOP_SIZE);
		ghostRepresentation.setFill(Color.CHOCOLATE);
	}
	
	public Coordinate getPosition(){
		return ghostCoord;
	}
	
	public Circle getGhost() {
		return ghostRepresentation;
	}

	public void setPosition(double x, double y, double z) {
		ghostCoord.setX(x);
		ghostCoord.setY(y);
		ghostCoord.setZ(z);
		this.updatePosition();
	}
	
	public void moveGhost(Vector vector) {
		ghostCoord.setX(ghostCoord.getX() + vector.getDeltaX());
		ghostCoord.setY(ghostCoord.getY() - vector.getDeltaY());
		ghostCoord.setZ(ghostCoord.getZ() + vector.getDeltaZ());
		this.updatePosition();
	}
	
	public Coordinate getNextCoordinate(Vector vector) {
		Coordinate coord = new Coordinate(ghostCoord.getX() + vector.getDeltaX(),
										  ghostCoord.getY() - vector.getDeltaY(),
										  ghostCoord.getZ() + vector.getDeltaZ());
		return coord;
	}
	
	public void resetToRight() {
		ghostCoord.setX(motorRight.getMotorCoordinate().getX());
		ghostCoord.setY(motorRight.getMotorCoordinate().getY());
		ghostCoord.setZ(motorRight.getMotorCoordinate().getZ());
		this.updatePosition();
	}
	
	public void updatePosition() {
		ghostRepresentation.setCenterX(ghostCoord.getX());
		ghostRepresentation.setCenterY(ghostCoord.getY());
		ghostRepresentation.setRadius(TOP_SIZE + ghostCoord.getZ());
	}

	public Coordinate getCoordinate(){
		return ghostCoord;
	}
}
