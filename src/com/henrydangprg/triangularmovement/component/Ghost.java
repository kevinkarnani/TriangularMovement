package com.henrydangprg.triangularmovement.component;

import javafx.scene.shape.Circle;

public class Ghost {

	private Motor motorLeft;
	private Motor motorTop;
	private Motor motorRight;
	private Circle ghostRepresentation;
	
	public Ghost(Motor motorLeft, Motor motorTop, Motor motorRight){
		this.motorLeft = motorLeft;
		this.motorTop = motorTop;
		this.motorRight = motorRight;
		ghostRepresentation = new Circle(); 
		ghostRepresentation.setRadius(10);
	}
	
	public Coordinate getPosition(){
		//Stub method
		return null;
	}
}
