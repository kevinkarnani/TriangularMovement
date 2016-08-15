package com.henrydangprg.triangularmovement.component;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ghost {

	private Motor motorLeft;
	private Motor motorTop;
	private Motor motorRight;

	protected static Circle ghostRepresentation;
	Triangle triangle = new Triangle();

	final double GHOST_STARTING_X = triangle.getRIGHT_VERTEX_X();
	final double GHOST_STARTING_Y = triangle.getRIGHT_VERTEX_Y();

	public Ghost(Motor motorLeft, Motor motorTop, Motor motorRight) {
		this.motorLeft = motorLeft;
		this.motorTop = motorTop;
		this.motorRight = motorRight;
	}

	public Circle setPosition() {
		ghostRepresentation = new Circle();
		ghostRepresentation.setRadius(10);
		ghostRepresentation.setFill(Color.CRIMSON);
		ghostRepresentation.setCenterX(GHOST_STARTING_X);
		ghostRepresentation.setCenterY(GHOST_STARTING_Y);
		return ghostRepresentation;
	}

	public void getGhostPositionInSimulation() {
		final double centerX = Ghost.ghostRepresentation.getBoundsInLocal()
				.getWidth() / 2;
		final double centerY = Ghost.ghostRepresentation.getBoundsInLocal()
				.getHeight() / 2;

		double xPosition = centerX + Ghost.ghostRepresentation.getLayoutX();
		double yPosition = centerY + Ghost.ghostRepresentation.getLayoutY();
	}

	public Coordinate getPosition() {
		Coordinate coordinate = new Coordinate(100, 100);
		return coordinate;
	}
}
