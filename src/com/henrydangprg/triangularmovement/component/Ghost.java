package com.henrydangprg.triangularmovement.component;

import javafx.scene.shape.Circle;

public class Ghost {

	private Motor motorLeft;
	private Motor motorTop;
	private Motor motorRight;
	
	public Ghost(Motor motorLeft, Motor motorTop, Motor motorRight){
		this.motorLeft = motorLeft;
		this.motorTop = motorTop;
		this.motorRight = motorRight;
	}
	
	public Coordinate getPosition(){
		//Stub method
		return null;
	}
}
