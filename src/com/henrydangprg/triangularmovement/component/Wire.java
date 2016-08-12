package com.henrydangprg.triangularmovement.component;

import javafx.scene.shape.Line;

public class Wire {

	Ghost ghost;
	Line leftWire;
	Line topWire;
	Line rightWire;

	// Place your actual wires here as lines
	public Wire(Ghost ghost, Line leftWire, Line topWire, Line rightWire) {
		this.ghost = ghost;
		this.leftWire = leftWire;
		this.topWire = topWire;
		this.rightWire = rightWire;
	}

	public Line getLeftWire() {
		return leftWire;
	}

	public Line getRightWire() {
		return rightWire;
	}

	public Line getTopWire() {
		return topWire;
	}
}
