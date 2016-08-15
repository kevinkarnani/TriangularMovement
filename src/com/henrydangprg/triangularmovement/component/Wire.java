package com.henrydangprg.triangularmovement.component;

import javafx.scene.shape.Line;

public class Wire {
	
	Line line = new Line();

	Ghost ghost;
	Motor motor;
	
	//Place your actual wires here as lines
	public Wire(Motor motor, Ghost ghost){
		this.motor = motor;
		this.ghost = ghost;
		line.setStartX(motor.getMotorPosition()[0]);
		line.setStartY(motor.getMotorPosition()[1]);
	}
	
	public void setWire() {
		line.setEndX(ghost.getPosition()[0]);
		line.setEndY(ghost.getPosition()[1]);
	}
	
	public Line getWire() {
		return line;
	}
}
