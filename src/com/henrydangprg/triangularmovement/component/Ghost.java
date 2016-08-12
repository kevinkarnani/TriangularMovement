package com.henrydangprg.triangularmovement.component;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ghost {

	private Motor motorLeft;
	private Motor motorTop;
	private Motor motorRight;
	protected static Circle ghostRepresentation;
	public static final double GHOST_STARTING_X = Triangle.RIGHT_VERTEX_X;
	public static final double GHOST_STARTING_Y = Triangle.RIGHT_VERTEX_Y;

	public Ghost(Motor motorLeft, Motor motorTop, Motor motorRight) {
		this.motorLeft = motorLeft;
		this.motorTop = motorTop;
		this.motorRight = motorRight;
		ghostRepresentation = new Circle();
		ghostRepresentation.setRadius(10);
	}

	public Circle setPosition() {
		ghostRepresentation = new Circle();
		ghostRepresentation.setRadius(10);
		ghostRepresentation.setFill(Color.CRIMSON);
		ghostRepresentation.setCenterX(GHOST_STARTING_X);
		ghostRepresentation.setCenterY(GHOST_STARTING_Y);
		return ghostRepresentation;
	}

	public Coordinate getPosition() {
		// Stub method
		return null;
	}
}
