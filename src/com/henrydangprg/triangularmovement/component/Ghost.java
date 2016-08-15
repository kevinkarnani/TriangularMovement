package com.henrydangprg.triangularmovement.component;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ghost {

	private Motor motorLeft;
	private Motor motorTop;
	private Motor motorRight;
	private Circle ghostRepresentation;
	
	private Coordinate coord = new Coordinate();
	
	public Ghost(Motor motorLeft, Motor motorTop, Motor motorRight){
		this.motorLeft = motorLeft;
		this.motorTop = motorTop;
		this.motorRight = motorRight;
		ghostRepresentation = new Circle(); 
		ghostRepresentation.setRadius(10);
		ghostRepresentation.setFill(Color.CHOCOLATE);
	}
	
	public double[] getPosition(){
		return coord.getXY();
	}
	
	public Circle getGhost() {
		return ghostRepresentation;
	}
	
	public void setPosition() {
	}
	
	public void resetToCenter(Triangle tri) {
		coord.setX((tri.getLeftVertex()[0] + tri.getRightVertex()[0] + tri.getTopVertex()[0])/3);
		coord.setY((tri.getLeftVertex()[1] + tri.getRightVertex()[1] + tri.getTopVertex()[1])/3);
		ghostRepresentation.setCenterX(coord.getX());
		ghostRepresentation.setCenterY(coord.getY());
	}
	
	public Coordinate getCoordinate(){
		return coord;
	}
}
