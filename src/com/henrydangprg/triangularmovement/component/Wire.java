package com.henrydangprg.triangularmovement.component;

import javafx.scene.shape.Line;

public class Wire {

	Ghost ghost;
	Line leftWire;
	Line topWire;
	Line rightWire;
	private static double xPosition;
	private static double yPosition;

	// Place your actual wires here as lines
	public Wire(Ghost ghost) {
		this.ghost = ghost;

		getLeftWire();
		getRightWire();
		getTopWire();
	}

	public Line getLeftWire() {
		Line leftLine = new Line(Triangle.LEFT_VERTEX_X,
				Triangle.LEFT_VERTEX_Y, Ghost.GHOST_STARTING_X,
				Ghost.GHOST_STARTING_Y);
		leftLine.setEndX(xPosition);
		leftLine.setEndY(yPosition);
		this.leftWire = leftLine;
		return leftWire;
	}

	public Line getRightWire() {
		Line rightLine = new Line(Triangle.RIGHT_VERTEX_X,
				Triangle.RIGHT_VERTEX_Y, Ghost.GHOST_STARTING_X,
				Ghost.GHOST_STARTING_Y);
		rightLine.setEndX(xPosition);
		rightLine.setEndY(yPosition);
		this.rightWire = rightLine;
		return rightWire;
	}

	public Line getTopWire() {
		Line topLine = new Line(Triangle.TOP_VERTEX_X, Triangle.TOP_VERTEX_Y,
				Ghost.GHOST_STARTING_X, Ghost.GHOST_STARTING_Y);
		moveGhostWithWire();
		topLine.setEndX(xPosition);
		topLine.setEndY(yPosition);
		this.topWire = topLine;
		return topWire;
	}

	public static void moveGhostWithWire() {
		final double centerX = Ghost.ghostRepresentation.getBoundsInLocal()
				.getWidth() / 2;
		final double centerY = Ghost.ghostRepresentation.getBoundsInLocal()
				.getHeight() / 2;

		xPosition = centerX + Ghost.ghostRepresentation.getLayoutX();
		yPosition = centerY + Ghost.ghostRepresentation.getLayoutY();
	}
}
